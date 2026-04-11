import "dart:ui";

import "package:flutter/material.dart" as m3;
import "package:flutter_svg/flutter_svg.dart";
import "package:nxdesign/colors.dart";
import "package:nxdesign/metrics.dart";

class TextButton extends m3.StatelessWidget {
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
  final m3.BorderRadius? borderRadius;
  final m3.VoidCallback? onPressed;
  final m3.Color? highlightColor;

  @override
  m3.Widget build(m3.BuildContext context) {
    final isDark = m3.Theme.of(context).brightness == m3.Brightness.dark;
    final radius = borderRadius ?? NxMetrics.defaultBorderRadius;

    return m3.SizedBox(
      height: height ?? m3.kMinInteractiveDimension,
      width: width,
      child: m3.Material(
        elevation: 0,
        color: isDark
            ? NxColors.darkThemeListItem
            : NxColors.lightThemeListItem,
        borderRadius: radius,
        child: m3.InkWell(
          onTap: onPressed,
          borderRadius: radius,
          highlightColor: highlightColor?.withAlpha(25),
          child: m3.Center(
            child: m3.Text(text, style: m3.TextStyle(color: highlightColor)),
          ),
        ),
      ),
    );
  }
}

class EditorDialog extends m3.StatelessWidget {
  const EditorDialog({
    required this.children,
    this.spacing = 4,
    this.insets = const m3.EdgeInsets.symmetric(horizontal: 28),
    this.padding = const m3.EdgeInsets.all(16),
    this.alignment = m3.CrossAxisAlignment.center,
    super.key,
  });

  final List<m3.Widget> children;
  final double spacing;
  final m3.EdgeInsets insets;
  final m3.EdgeInsets padding;
  final m3.CrossAxisAlignment alignment;

  @override
  m3.Widget build(m3.BuildContext context) {
    return m3.Dialog(
      insetPadding: insets,
      child: m3.Padding(
        padding: padding,
        child: m3.SingleChildScrollView(
          child: m3.Column(
            spacing: spacing,
            mainAxisSize: m3.MainAxisSize.min,
            crossAxisAlignment: alignment,
            children: children,
          ),
        ),
      ),
    );
  }
}

class ConfirmActionDialog extends m3.StatelessWidget {
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
  m3.Widget build(m3.BuildContext context) {
    return m3.AlertDialog(
      actionsPadding: const m3.EdgeInsets.all(16),
      contentPadding: const m3.EdgeInsets.symmetric(
        horizontal: 48,
        vertical: 16,
      ),
      title: m3.Text(titleText, textAlign: m3.TextAlign.center),
      content: m3.Text(infoText, textAlign: m3.TextAlign.center),
      actions: [
        TextButton(
          height: 56,
          width: double.infinity,
          text: confirmText ?? "OK",
          borderRadius: NxMetrics.startBorderRadius,
          onPressed: () => m3.Navigator.of(context).pop(true),
          highlightColor: NxColors.nothingRed,
        ),
        const m3.SizedBox(height: 2),
        TextButton(
          height: 56,
          width: double.infinity,
          text: cancelText ?? "CANCEL",
          borderRadius: NxMetrics.endBorderRadius,
          onPressed: () => m3.Navigator.of(context).pop(false),
        ),
      ],
    );
  }
}

class AppBar extends m3.StatelessWidget {
  const AppBar({
    super.key,
    this.title,
    this.titleStyle,
    this.centerTitle = false,
    this.padding,
    this.actions,
  });

  final String? title;
  final m3.TextStyle? titleStyle;
  final bool centerTitle;
  final List<m3.Widget>? actions;
  final m3.EdgeInsets? padding;

  @override
  m3.Widget build(m3.BuildContext context) {
    final canPop = m3.Navigator.of(context).canPop();
    final isHome = m3.ModalRoute.of(context)?.settings.name == "/";
    final hasTitle = title != null;
    final effectivePadding = canPop
        ? m3.EdgeInsets.zero
        : hasTitle
        ? const m3.EdgeInsets.only(left: 24)
        : padding ?? m3.EdgeInsets.zero;

    return m3.Padding(
      padding: effectivePadding,
      child: m3.AppBar(
        titleSpacing: 0,
        centerTitle: centerTitle,
        automaticallyImplyLeading: false,
        leading: canPop && !isHome
            ? m3.IconButton(
                tooltip: "Back",
                onPressed: () => m3.Navigator.of(context).pop(),
                icon: const NxIcon(path: NxIcon.back),
                padding: const m3.EdgeInsets.all(14),
              )
            : null,
        title: hasTitle
            ? m3.Text(
                title!,
                style: titleStyle,
                strutStyle: m3.StrutStyle(
                  forceStrutHeight: true,
                  fontSize: titleStyle?.fontSize,
                ),
              )
            : null,
        actions: actions,
      ),
    );
  }
}

class NavigationItem {
  final m3.Widget destination;
  final String text;
  final String icon;
  final String? tooltip;

  NavigationItem({
    required this.destination,
    required this.text,
    required this.icon,
    this.tooltip,
  });
}

class NavBar extends m3.StatefulWidget {
  const NavBar({
    required this.items,
    required this.onIndexChanged,
    this.selectedIndex = 0,
    super.key,
  });

  final List<NavigationItem> items;
  final Function(int index) onIndexChanged;
  final int selectedIndex;

  @override
  m3.State<NavBar> createState() => _BottomNavigationBarState();
}

class _BottomNavigationBarState extends m3.State<NavBar> {
  bool get _isDark => m3.Theme.of(context).brightness == Brightness.dark;

  @override
  m3.Widget build(m3.BuildContext context) {
    return m3.Material(
      color: _isDark ? NxColors.darkThemeListItem : NxColors.lightThemeListItem,
      shape: const m3.StadiumBorder(),
      child: m3.SizedBox(
        height: 72,
        child: m3.Padding(
          padding: const m3.EdgeInsets.symmetric(horizontal: 16),
          child: m3.Row(
            mainAxisSize: m3.MainAxisSize.max,
            crossAxisAlignment: m3.CrossAxisAlignment.stretch,
            children: [
              for (final item in widget.items)
                m3.Expanded(
                  child: m3.GestureDetector(
                    onTap: () {
                      setState(() {
                        widget.onIndexChanged.call(widget.items.indexOf(item));
                      });
                    },
                    child: m3.Tooltip(
                      message: item.text,
                      enableFeedback: true,
                      verticalOffset: 40,
                      preferBelow: false,
                      child: m3.Center(
                        child: NxIcon(
                          size: 24,
                          path: item.icon,
                          selected:
                              widget.selectedIndex ==
                              widget.items.indexOf(item),
                        ),
                      ),
                    ),
                  ),
                ),
            ],
          ),
        ),
      ),
    );
  }
}

class Dismissible extends m3.StatefulWidget {
  const Dismissible({
    required this.onDismissed,
    required this.background,
    required this.child,
    this.confirmDismiss,
    this.dismissThreshold = 0.3,
    super.key,
  });

  final m3.VoidCallback onDismissed;
  final m3.Widget background;
  final m3.Widget child;
  final double dismissThreshold;
  final Future<bool?> Function()? confirmDismiss;

  @override
  m3.State<Dismissible> createState() => _DismissibleState();
}

class _DismissibleState extends m3.State<Dismissible>
    with m3.TickerProviderStateMixin {
  final _childKey = m3.GlobalKey();

  late m3.AnimationController _dragController;
  late m3.Animation<double> _dragAnimation;

  var _dragExtent = 0.0;
  var _maxWidth = 0.0;
  var _dismissed = false;
  var _notifiedDismiss = false;

  double? _currentHeight;
  m3.Size? _childSize;

  @override
  void initState() {
    super.initState();

    m3.WidgetsBinding.instance.addPostFrameCallback((_) {
      _measureChild();
    });

    _dragController = m3.AnimationController(
      vsync: this,
      duration: const Duration(milliseconds: 200),
    );

    _dragAnimation = m3.Tween<double>(begin: 0, end: 0).animate(_dragController)
      ..addListener(() {
        setState(() {
          _dragExtent = _dragAnimation.value;
        });
      });
  }

  @override
  void didUpdateWidget(covariant Dismissible oldWidget) {
    super.didUpdateWidget(oldWidget);
    m3.WidgetsBinding.instance.addPostFrameCallback((_) {
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

    final box = context.findRenderObject() as m3.RenderBox;
    final size = box.size;

    setState(() {
      _childSize = size;
    });
  }

  Future<void> _handleDragUpdate(m3.DragUpdateDetails details) async {
    _dragExtent += details.delta.dx;

    if (_dragExtent > 0) {
      _dragExtent = 0;
    }
    if (_dragExtent.abs() > _maxWidth) {
      _dragExtent = -_maxWidth;
    }

    setState(() {});
  }

  Future<void> _handleDragEnd(m3.DragEndDetails details) async {
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
        m3.Tween<double>(begin: _dragExtent, end: target).animate(
          m3.CurvedAnimation(parent: _dragController, curve: m3.Curves.easeOut),
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
  m3.Widget build(m3.BuildContext context) {
    return m3.LayoutBuilder(
      builder: (context, constraints) {
        _maxWidth = constraints.maxWidth;
        _currentHeight ??= _childSize?.height ?? constraints.maxHeight;

        final revealWidth = _dragExtent.abs();

        return m3.AnimatedSize(
          duration: const Duration(milliseconds: 150),
          onEnd: () {
            if (_dismissed && !_notifiedDismiss) {
              _notifiedDismiss = true;
              widget.onDismissed.call();
            }
          },
          child: m3.SizedBox(
            height: _dismissed ? 0 : null,
            child: m3.GestureDetector(
              onHorizontalDragUpdate: _handleDragUpdate,
              onHorizontalDragEnd: _handleDragEnd,
              child: m3.SizedBox(
                width: _maxWidth,
                child: m3.Stack(
                  children: [
                    m3.Align(
                      alignment: m3.AlignmentGeometry.centerRight,
                      child: m3.Transform.translate(
                        offset: Offset(_dragExtent, 0),
                        child: m3.SizedBox(
                          key: _childKey,
                          width: _maxWidth,
                          child: widget.child,
                        ),
                      ),
                    ),
                    m3.Align(
                      alignment: m3.AlignmentGeometry.centerRight,
                      child: m3.SizedBox(
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

class NxIcon extends m3.StatelessWidget {
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
  final m3.Color? color;
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
  static const history = "icons/history.svg";
  static const backspace = "icons/backspace.svg";
  static const function = "icons/function.svg";
  static const right = "icons/right.svg";
  static const dayView = "icons/day-view.svg";
  static const monthView = "icons/month-view.svg";
  static const filter = "icons/filter.svg";
  static const left = "icons/left.svg";
  static const location = "icons/location.svg";

  @override
  m3.Widget build(m3.BuildContext context) {
    final isDark = m3.Theme.of(context).brightness == m3.Brightness.dark;
    final unselectedColor = isDark
        ? NxColors.darkThemeText
        : NxColors.lightThemeText;

    return m3.SizedBox.square(
      dimension: size,
      child: SvgPicture.asset(
        path,
        package: package,
        fit: m3.BoxFit.contain,
        colorFilter: m3.ColorFilter.mode(
          selected ? NxColors.nothingRed : color ?? unselectedColor,
          m3.BlendMode.srcIn,
        ),
      ),
    );
  }
}

class Checkbox extends m3.StatelessWidget {
  const Checkbox({required this.value, this.onChanged, super.key});

  final bool value;
  final m3.ValueChanged<bool>? onChanged;

  @override
  m3.Widget build(m3.BuildContext context) {
    const duration = Duration(milliseconds: 150);

    return m3.SizedBox.square(
      dimension: 48,
      child: m3.InkWell(
        customBorder: const m3.CircleBorder(),
        onTap: () => onChanged?.call(!value),
        child: m3.Center(
          child: m3.SizedBox.square(
            dimension: 20,
            child: m3.AnimatedContainer(
              duration: duration,
              curve: m3.Curves.easeInOut,
              decoration: m3.BoxDecoration(
                color: value ? NxColors.lightThemeCard : null,
                borderRadius: m3.BorderRadius.circular(4),
                border: value
                    ? null
                    : m3.Border.all(color: NxColors.lightThemeCard, width: 2),
              ),
              child: m3.Center(
                child: m3.AnimatedOpacity(
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

class Switch extends m3.StatelessWidget {
  const Switch({required this.value, this.onChanged, super.key});

  final bool value;
  final m3.ValueChanged<bool>? onChanged;

  @override
  m3.Widget build(m3.BuildContext context) {
    const width = 48.0;
    const height = 48.0;
    const duration = Duration(milliseconds: 250);

    final isDark = m3.Theme.of(context).brightness == m3.Brightness.dark;
    final activeColor = isDark
        ? NxColors.lightThemeBackground
        : NxColors.darkThemeBackground;
    final inactiveColor = isDark
        ? NxColors.darkInactive
        : NxColors.lightInactive;
    final thumbColor = isDark
        ? NxColors.darkThemeBackground
        : NxColors.lightThemeBackground;

    return m3.SizedBox(
      height: height,
      width: width,
      child: m3.InkWell(
        customBorder: const m3.CircleBorder(),
        onTap: () => onChanged?.call(!value),
        child: m3.Center(
          child: m3.AnimatedContainer(
            height: 24,
            width: double.infinity,
            duration: duration,
            padding: const m3.EdgeInsets.all(2),
            decoration: m3.BoxDecoration(
              borderRadius: m3.BorderRadius.circular(height / 2),
              color: value ? activeColor : inactiveColor,
            ),
            child: m3.Stack(
              children: [
                m3.AnimatedAlign(
                  alignment: value
                      ? m3.Alignment.centerRight
                      : m3.Alignment.centerLeft,
                  duration: duration,
                  curve: m3.Curves.easeInOut,
                  child: m3.Container(
                    width: 20,
                    height: 20,
                    decoration: m3.BoxDecoration(
                      shape: m3.BoxShape.circle,
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

class Radio extends m3.StatelessWidget {
  const Radio({required this.value, super.key, this.onChanged});

  final bool value;
  final m3.ValueChanged<bool>? onChanged;

  @override
  m3.Widget build(m3.BuildContext context) {
    final isDark = m3.Theme.of(context).brightness == m3.Brightness.dark;

    return m3.SizedBox.square(
      dimension: 48,
      child: m3.InkWell(
        onTap: () => onChanged?.call(!value),
        customBorder: const m3.CircleBorder(),
        child: m3.Center(
          child: m3.SizedBox.square(
            dimension: 20,
            child: m3.DecoratedBox(
              decoration: m3.BoxDecoration(
                shape: m3.BoxShape.circle,
                border: m3.Border.all(
                  width: 2,
                  color: isDark
                      ? NxColors.lightThemeCard
                      : value
                      ? NxColors.darkThemeCard
                      : NxColors.lightThemeCard,
                ),
              ),
              child: m3.Padding(
                padding: const m3.EdgeInsets.all(6),
                child: value
                    ? m3.SizedBox.expand(
                        child: m3.DecoratedBox(
                          decoration: m3.BoxDecoration(
                            shape: m3.BoxShape.circle,
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

class MultiSetting<T> extends m3.StatelessWidget {
  const MultiSetting({
    required this.selected,
    required this.selectables,
    this.onSelectionChanged,
    super.key,
  });

  final T selected;
  final List<MultiSettingData<T>> selectables;
  final Function(T selection)? onSelectionChanged;

  @override
  m3.Widget build(m3.BuildContext context) {
    return m3.DecoratedBox(
      decoration: m3.BoxDecoration(
        borderRadius: NxMetrics.largeBorderRadius,
        border: m3.Border.all(color: m3.Colors.grey.withAlpha(80)),
      ),
      child: m3.Row(
        mainAxisSize: m3.MainAxisSize.max,
        mainAxisAlignment: m3.MainAxisAlignment.spaceBetween,
        children: [
          for (var i = 0; i < selectables.length; i++)
            m3.Expanded(
              child: m3.Padding(
                padding: const m3.EdgeInsets.all(1),
                child: m3.Material(
                  color: selected == selectables[i].data
                      ? m3.Colors.grey.withAlpha(80)
                      : m3.Colors.transparent,
                  borderRadius: m3.BorderRadius.horizontal(
                    left: m3.Radius.circular(i == 0 ? 12 - 1 : 0),
                    right: m3.Radius.circular(
                      i == selectables.length - 1 ? 12 - 1 : 0,
                    ),
                  ),
                  child: m3.InkWell(
                    borderRadius: m3.BorderRadius.horizontal(
                      left: m3.Radius.circular(i == 0 ? 12 - 1 : 0),
                      right: m3.Radius.circular(
                        i == selectables.length - 1 ? 12 - 1 : 0,
                      ),
                    ),
                    onTap: () {
                      onSelectionChanged?.call(selectables[i].data);
                    },
                    child: m3.Padding(
                      padding: const m3.EdgeInsets.symmetric(vertical: 8),
                      child: m3.Column(
                        mainAxisSize: m3.MainAxisSize.max,
                        mainAxisAlignment: m3.MainAxisAlignment.center,
                        children: [
                          if (selectables[i].icon != null)
                            m3.Padding(
                              padding: const m3.EdgeInsets.symmetric(
                                vertical: 8,
                              ),
                              child: m3.SizedBox.square(
                                dimension: 32,
                                child: selectables[i].icon,
                              ),
                            ),
                          m3.Text(selectables[i].label),
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

class MultiSettingData<T> {
  final String label;
  final m3.Widget? icon;
  final T data;

  const MultiSettingData({required this.label, required this.data, this.icon});
}
