import "package:flutter/material.dart"
    hide TextButton, Radio, Switch, Checkbox, Dismissible;
import "package:flutter_svg/flutter_svg.dart";
import "package:nxdesign/colors.dart";
import "package:nxdesign/metrics.dart";

class TextButton extends StatelessWidget {
  const TextButton({
    required this.text,
    this.width,
    this.height,
    this.borderRadius,
    this.highlightColor,
    this.onPressed,
    super.key,
  });

  final String text;
  final double? width;
  final double? height;
  final BorderRadius? borderRadius;
  final VoidCallback? onPressed;
  final Color? highlightColor;

  @override
  Widget build(BuildContext context) {
    final isDark = Theme.of(context).brightness == Brightness.dark;
    final radius = borderRadius ?? NxMetrics.defaultBorderRadius;

    return SizedBox(
      height: height ?? kMinInteractiveDimension,
      width: width,
      child: Material(
        elevation: 0,
        color: isDark
            ? NxColors.darkThemeListItem
            : NxColors.lightThemeListItem,
        borderRadius: radius,
        child: InkWell(
          onTap: onPressed,
          borderRadius: radius,
          highlightColor: highlightColor?.withAlpha(25),
          child: Center(
            child: Text(text, style: TextStyle(color: highlightColor)),
          ),
        ),
      ),
    );
  }
}

class EditorDialog extends StatelessWidget {
  const EditorDialog({required this.children, super.key});

  final List<Widget> children;

  @override
  Widget build(BuildContext context) {
    return Dialog(
      insetPadding: const EdgeInsets.symmetric(horizontal: 28),
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: SingleChildScrollView(
          child: Column(mainAxisSize: MainAxisSize.min, children: children),
        ),
      ),
    );
  }
}

class ConfirmActionDialog extends StatelessWidget {
  const ConfirmActionDialog({
    required this.titleText,
    required this.infoText,
    super.key,
    this.confirmText,
    this.cancelText,
    this.isWarning,
  });

  final String titleText;
  final String infoText;
  final String? confirmText;
  final String? cancelText;
  final bool? isWarning;

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      actionsPadding: const EdgeInsets.all(16),
      contentPadding: const EdgeInsets.symmetric(horizontal: 48, vertical: 16),
      title: Text(titleText, textAlign: TextAlign.center),
      content: Text(infoText, textAlign: TextAlign.center),
      actions: [
        TextButton(
          height: 56,
          width: double.infinity,
          text: confirmText ?? "OK",
          borderRadius: NxMetrics.startBorderRadius,
          onPressed: () => Navigator.of(context).pop(true),
          highlightColor: NxColors.nothingRed,
        ),
        const SizedBox(height: 2),
        TextButton(
          height: 56,
          width: double.infinity,
          text: cancelText ?? "CANCEL",
          borderRadius: NxMetrics.endBorderRadius,
          onPressed: () => Navigator.of(context).pop(false),
        ),
      ],
    );
  }
}

class Dismissible extends StatefulWidget {
  const Dismissible({
    required this.onDismissed,
    required this.background,
    required this.child,
    this.confirmDismiss,
    this.dismissThreshold = 0.3,
    super.key,
  });

  final VoidCallback onDismissed;
  final Widget background;
  final Widget child;
  final double dismissThreshold;
  final Future<bool?> Function()? confirmDismiss;

  @override
  State<Dismissible> createState() => _DismissibleState();
}

class _DismissibleState extends State<Dismissible>
    with TickerProviderStateMixin {
  final _childKey = GlobalKey();

  late AnimationController _dragController;
  late Animation<double> _dragAnimation;

  var _dragExtent = 0.0;
  var _maxWidth = 0.0;
  var _dismissed = false;
  var _notifiedDismiss = false;

  double? _currentHeight;
  Size? _childSize;

  @override
  void initState() {
    super.initState();

    WidgetsBinding.instance.addPostFrameCallback((_) {
      _measureChild();
    });

    _dragController = AnimationController(
      vsync: this,
      duration: const Duration(milliseconds: 200),
    );

    _dragAnimation = Tween<double>(begin: 0, end: 0).animate(_dragController)
      ..addListener(() {
        setState(() {
          _dragExtent = _dragAnimation.value;
        });
      });
  }

  @override
  void didUpdateWidget(covariant Dismissible oldWidget) {
    super.didUpdateWidget(oldWidget);
    WidgetsBinding.instance.addPostFrameCallback((_) {
      _measureChild();
    });
  }

  @override
  void dispose() {
    _dragController.dispose();
    super.dispose();
  }

  void _measureChild() {
    final context = _childKey.currentContext;
    if (context == null) {
      return;
    }

    final box = context.findRenderObject() as RenderBox;
    final size = box.size;

    setState(() {
      _childSize = size;
    });
  }

  Future<void> _handleDragUpdate(DragUpdateDetails details) async {
    _dragExtent += details.delta.dx;

    if (_dragExtent > 0) {
      _dragExtent = 0;
    }
    if (_dragExtent.abs() > _maxWidth) {
      _dragExtent = -_maxWidth;
    }

    setState(() {});
  }

  Future<void> _handleDragEnd(DragEndDetails details) async {
    final progress = _dragExtent.abs() / _maxWidth;

    if (progress <= widget.dismissThreshold) {
      await _animateTo(0);
      return;
    }

    final allowed = await widget.confirmDismiss?.call();

    if (allowed == null || allowed == false) {
      await _animateTo(0);
      return;
    }

    await _animateTo(-_maxWidth);
  }

  Future<void> _animateTo(double target) async {
    _dragAnimation =
        Tween<double>(begin: _dragExtent, end: target).animate(
          CurvedAnimation(parent: _dragController, curve: Curves.easeOut),
        )..addListener(() {
          setState(() {
            _dragExtent = _dragAnimation.value;
          });
        });

    _dragController.reset();
    await _dragController.forward();

    if (target.abs() == _maxWidth) {
      setState(() {
        _dismissed = true;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        _maxWidth = constraints.maxWidth;
        _currentHeight ??= _childSize?.height ?? constraints.maxHeight;

        final revealWidth = _dragExtent.abs();

        return AnimatedSize(
          duration: const Duration(milliseconds: 150),
          onEnd: () {
            if (_dismissed && !_notifiedDismiss) {
              _notifiedDismiss = true;
              widget.onDismissed.call();
            }
          },
          child: SizedBox(
            height: _dismissed ? 0 : null,
            child: GestureDetector(
              onHorizontalDragUpdate: _handleDragUpdate,
              onHorizontalDragEnd: _handleDragEnd,
              child: SizedBox(
                width: _maxWidth,
                child: Stack(
                  children: [
                    Align(
                      alignment: AlignmentGeometry.centerRight,
                      child: Transform.translate(
                        offset: Offset(_dragExtent, 0),
                        child: SizedBox(
                          key: _childKey,
                          width: _maxWidth,
                          child: widget.child,
                        ),
                      ),
                    ),
                    Align(
                      alignment: AlignmentGeometry.centerRight,
                      child: SizedBox(
                        width: revealWidth.clamp(revealWidth, _maxWidth),
                        height: _childSize?.height,
                        child: widget.background,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
        );
      },
    );
  }
}

class NxIcon extends StatelessWidget {
  const NxIcon({
    required this.path,
    this.package = "nxdesign",
    this.size = 26,
    this.selected = false,
    this.color,
    super.key,
  });

  final String path;
  final String package;
  final double size;
  final Color? color;
  final bool selected;

  static const add = "icons/add.svg";
  static const alarm = "icons/alarm.svg";
  static const apps = "icons/apps.svg";
  static const back = "icons/back.svg";
  static const block = "icons/block.svg";
  static const call = "icons/call.svg";
  static const check = "icons/check.svg";
  static const close = "icons/close.svg";
  static const compress = "icons/compress.svg";
  static const delete = "icons/delete.svg";
  static const deleteSwipe = "icons/delete_swipe.svg";
  static const document = "icons/document.svg";
  static const download = "icons/download.svg";
  static const down = "icons/down.svg";
  static const folder = "icons/folder.svg";
  static const globe = "icons/globe.svg";
  static const gridview = "icons/gridview.svg";
  static const home = "icons/home.svg";
  static const hourglass = "icons/hourglass.svg";
  static const image = "icons/image.svg";
  static const inbox = "icons/inbox.svg";
  static const info = "icons/info.svg";
  static const leftPanelClose = "icons/left_panel_close.svg";
  static const leftPanelOpen = "icons/left_panel_open.svg";
  static const listview = "icons/listview.svg";
  static const lockClose = "icons/lock_close.svg";
  static const lockOpen = "icons/lock_open.svg";
  static const more = "icons/more.svg";
  static const movie = "icons/movie.svg";
  static const notifications = "icons/notifications.svg";
  static const notification = "icons/notification.svg";
  static const pause = "icons/pause.svg";
  static const person = "icons/person.svg";
  static const play = "icons/play.svg";
  static const rename = "icons/rename.svg";
  static const reset = "icons/reset.svg";
  static const search = "icons/search.svg";
  static const settings = "icons/settings.svg";
  static const share = "icons/share.svg";
  static const soundOff = "icons/sound_off.svg";
  static const soundOn = "icons/sound_on.svg";
  static const starred = "icons/starred.svg";
  static const star = "icons/star.svg";
  static const timer = "icons/timer.svg";
  static const unarchive = "icons/unarchive.svg";
  static const up = "icons/up.svg";
  static const vibrate = "icons/vibrate.svg";

  @override
  Widget build(BuildContext context) {
    final isDark = Theme.of(context).brightness == Brightness.dark;
    final unselectedColor = isDark
        ? NxColors.darkThemeText
        : NxColors.lightThemeText;

    return SizedBox.square(
      dimension: size,
      child: SvgPicture.asset(
        path,
        package: package,
        fit: BoxFit.contain,
        colorFilter: ColorFilter.mode(
          selected ? NxColors.nothingRed : color ?? unselectedColor,
          BlendMode.srcIn,
        ),
      ),
    );
  }
}

class Checkbox extends StatelessWidget {
  const Checkbox({required this.value, this.onChanged, super.key});

  final bool value;
  final ValueChanged<bool>? onChanged;

  @override
  Widget build(BuildContext context) {
    const duration = Duration(milliseconds: 150);

    return SizedBox.square(
      dimension: 48,
      child: InkWell(
        customBorder: const CircleBorder(),
        onTap: () => onChanged?.call(!value),
        child: Center(
          child: SizedBox.square(
            dimension: 20,
            child: AnimatedContainer(
              duration: duration,
              curve: Curves.easeInOut,
              decoration: BoxDecoration(
                color: value ? NxColors.lightThemeCard : null,
                borderRadius: BorderRadius.circular(4),
                border: value
                    ? null
                    : Border.all(color: NxColors.lightThemeCard, width: 2),
              ),
              child: Center(
                child: AnimatedOpacity(
                  duration: duration,
                  opacity: value ? 1.0 : 0.0,
                  child: const NxIcon(
                    path: "icons/checkbox_check.svg",
                    color: NxColors.darkThemeCard,
                  ),
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}

class Switch extends StatelessWidget {
  const Switch({required this.value, this.onChanged, super.key});

  final bool value;
  final ValueChanged<bool>? onChanged;

  @override
  Widget build(BuildContext context) {
    const width = 48.0;
    const height = 48.0;
    const duration = Duration(milliseconds: 250);

    final isDark = Theme.of(context).brightness == Brightness.dark;
    final activeColor = isDark
        ? NxColors.lightThemeBackground
        : NxColors.darkThemeBackground;
    final inactiveColor = isDark
        ? NxColors.darkInactive
        : NxColors.lightInactive;
    final thumbColor = isDark
        ? NxColors.darkThemeBackground
        : NxColors.lightThemeBackground;

    return SizedBox(
      height: height,
      width: width,
      child: InkWell(
        customBorder: const CircleBorder(),
        onTap: () => onChanged?.call(!value),
        child: Center(
          child: AnimatedContainer(
            height: 24,
            width: double.infinity,
            duration: duration,
            padding: const EdgeInsets.all(2),
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(height / 2),
              color: value ? activeColor : inactiveColor,
            ),
            child: Stack(
              children: [
                AnimatedAlign(
                  alignment: value
                      ? Alignment.centerRight
                      : Alignment.centerLeft,
                  duration: duration,
                  curve: Curves.easeInOut,
                  child: Container(
                    width: 20,
                    height: 20,
                    decoration: BoxDecoration(
                      shape: BoxShape.circle,
                      color: thumbColor,
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class Radio extends StatelessWidget {
  const Radio({required this.value, super.key, this.onChanged});

  final bool value;
  final ValueChanged<bool>? onChanged;

  @override
  Widget build(BuildContext context) {
    final isDark = Theme.of(context).brightness == Brightness.dark;

    return SizedBox.square(
      dimension: 48,
      child: InkWell(
        onTap: () => onChanged?.call(!value),
        customBorder: const CircleBorder(),
        child: Center(
          child: SizedBox.square(
            dimension: 20,
            child: DecoratedBox(
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                border: Border.all(
                  width: 2,
                  color: isDark
                      ? NxColors.lightThemeCard
                      : value
                      ? NxColors.darkThemeCard
                      : NxColors.lightThemeCard,
                ),
              ),
              child: Padding(
                padding: const EdgeInsets.all(6),
                child: value
                    ? SizedBox.expand(
                        child: DecoratedBox(
                          decoration: BoxDecoration(
                            shape: BoxShape.circle,
                            color: isDark
                                ? NxColors.lightThemeCard
                                : NxColors.darkThemeCard,
                          ),
                        ),
                      )
                    : null,
              ),
            ),
          ),
        ),
      ),
    );
  }
}

class MultiValueTile<T> extends StatelessWidget {
  const MultiValueTile({
    required this.selected,
    required this.selectables,
    this.onSelectionChanged,
    super.key,
  });

  final T selected;
  final List<MultiSetting<T>> selectables;
  final Function(T selection)? onSelectionChanged;

  @override
  Widget build(BuildContext context) {
    return DecoratedBox(
      decoration: BoxDecoration(
        borderRadius: NxMetrics.largeBorderRadius,
        border: Border.all(color: Colors.grey.withAlpha(80)),
      ),
      child: Row(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          for (var i = 0; i < selectables.length; i++)
            Expanded(
              child: Padding(
                padding: const EdgeInsets.all(1),
                child: Material(
                  color: selected == selectables[i].data
                      ? Colors.grey.withAlpha(80)
                      : Colors.transparent,
                  borderRadius: BorderRadius.horizontal(
                    left: Radius.circular(i == 0 ? 12 - 1 : 0),
                    right: Radius.circular(
                      i == selectables.length - 1 ? 12 - 1 : 0,
                    ),
                  ),
                  child: InkWell(
                    borderRadius: BorderRadius.horizontal(
                      left: Radius.circular(i == 0 ? 12 - 1 : 0),
                      right: Radius.circular(
                        i == selectables.length - 1 ? 12 - 1 : 0,
                      ),
                    ),
                    onTap: () {
                      onSelectionChanged?.call(selectables[i].data);
                    },
                    child: Padding(
                      padding: const EdgeInsets.symmetric(vertical: 8),
                      child: Column(
                        mainAxisSize: MainAxisSize.max,
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          if (selectables[i].icon != null)
                            Padding(
                              padding: const EdgeInsets.symmetric(vertical: 8),
                              child: SizedBox.square(
                                dimension: 32,
                                child: selectables[i].icon,
                              ),
                            ),
                          Text(selectables[i].label),
                        ],
                      ),
                    ),
                  ),
                ),
              ),
            ),
        ],
      ),
    );
  }
}

class MultiSetting<T> {
  final String label;
  final Widget? icon;
  final T data;

  const MultiSetting({required this.label, required this.data, this.icon});
}
