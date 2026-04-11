import "package:flutter/material.dart"
    hide Checkbox, Switch, Radio, Dismissible, TextButton, AppBar;
import "package:nxdesign/colors.dart";
import "package:nxdesign/fonts.dart";
import "package:nxdesign/metrics.dart";
import "package:nxdesign/widgets.dart";
import "package:nxdesign_example/colors.dart";
import "package:nxdesign_example/fonts.dart";

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  late final _widgets = [
    ListTile(
      title: const Text("Checkbox: checked"),
      trailing: Checkbox(value: true, onChanged: (value) {}),
      onTap: () {},
    ),
    ListTile(
      title: const Text("Checkbox: unchecked"),
      trailing: Checkbox(value: false, onChanged: (value) {}),
      onTap: () {},
    ),
    ListTile(
      title: const Text("Switch: on"),
      trailing: Switch(value: true, onChanged: (value) {}),
      onTap: () {},
    ),
    ListTile(
      title: const Text("Switch: off"),
      trailing: Switch(value: false, onChanged: (value) {}),
      onTap: () {},
    ),
    ListTile(
      title: const Text("Radio: on"),
      trailing: Radio(value: true, onChanged: (value) {}),
      onTap: () {},
    ),
    ListTile(
      title: const Text("Radio: off"),
      trailing: Radio(value: false, onChanged: (value) {}),
      onTap: () {},
    ),
    const ListTile(
      title: Text("MultiSetting"),
      subtitle: Padding(
        padding: EdgeInsets.only(top: 16, bottom: 8),
        child: MultiSetting(
          selected: 0,
          selectables: [
            MultiSettingData(label: "zero", data: 0),
            MultiSettingData(label: "one", data: 1),
            MultiSettingData(label: "two", data: 2),
          ],
        ),
      ),
    ),
    ListTile(
      title: const Text("Show Action Dialog"),
      onTap: () {
        showDialog(
          context: context,
          builder: (context) => const ConfirmActionDialog(
            titleText: "This is a sample",
            infoText: "Use this for actions that require confirmation",
            isWarning: true,
            confirmText: "Custom Confirm Text",
            cancelText: "Custom Cancel Text",
          ),
        );
      },
    ),
    ListTile(
      title: const Text("Show Normal Dialog"),
      onTap: () {
        showDialog(
          context: context,
          builder: (context) => EditorDialog(
            spacing: 16,
            alignment: CrossAxisAlignment.start,
            children: [
              const Text("You can use this as a dialog with custom children"),
              const Text("The default children padding is 16"),
              TextButton(
                borderRadius: NxMetrics.largeBorderRadius,
                onPressed: () => Navigator.of(context).pop(),
                text: "Close",
              ),
            ],
          ),
        );
      },
    ),
    ClipRRect(
      child: Dismissible(
        key: const ValueKey("custom-dismissible"),
        onDismissed: () {},
        background: Container(
          margin: const EdgeInsets.only(left: 4),
          decoration: const BoxDecoration(
            color: NxColors.nothingYellow,
            borderRadius: NxMetrics.largeBorderRadius,
          ),
          child: const NxIcon(path: NxIcon.back),
        ),
        child: const ListTile(title: Text("Dismissible list tile")),
      ),
    ),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
        preferredSize: const Size.fromHeight(kToolbarHeight),
        child: AppBar(
          title: "NxDesign Example",
          titleStyle: const TextStyle(
            fontFamily: NxFonts.fontNType,
            fontSize: 32,
          ),
          actions: [
            IconButton(
              tooltip: "sample tooltip",
              padding: const EdgeInsets.all(14),
              icon: const NxIcon(path: NxIcon.info),
              onPressed: () {},
            ),
          ],
        ),
      ),
      body: SafeArea(
        child: SingleChildScrollView(
          child: Padding(
            padding: const EdgeInsets.all(24),
            child: Column(
              spacing: 24,
              children: [
                Material(
                  child: ListTile(
                    title: const Text("View Fonts"),
                    trailing: const SizedBox(
                      width: 48,
                      child: NxIcon(path: NxIcon.right),
                    ),
                    onTap: () {
                      Navigator.of(context).push(
                        MaterialPageRoute(
                          builder: (context) => const FontsDisplayScreen(),
                        ),
                      );
                    },
                  ),
                ),
                Material(
                  child: ListTile(
                    title: const Text("View Colors"),
                    trailing: const SizedBox(
                      width: 48,
                      child: NxIcon(path: NxIcon.right),
                    ),
                    onTap: () {
                      Navigator.of(context).push(
                        MaterialPageRoute(
                          builder: (context) => const ColorsDisplayScreen(),
                        ),
                      );
                    },
                  ),
                ),
                const SizedBox(),
                const Text("Sample NxWidgets"),
                const Divider(),
                ..._widgets.map((widget) => Material(child: widget)),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
