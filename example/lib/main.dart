import 'package:flutter/material.dart' hide Checkbox, Switch, Radio;
import 'package:nxdesign/themes.dart';
import 'package:nxdesign/widgets.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  var _expanded = true;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: NxTheme.lightTheme,
      darkTheme: NxTheme.darkTheme,
      themeMode: ThemeMode.system,
      home: Scaffold(
        appBar: AppBar(title: const Text('NxDesign example app')),
        body: SafeArea(
          child: Padding(
            padding: const EdgeInsets.all(24),
            child: Column(
              spacing: 24,
              children: [
                ListTile(title: Text("Sample normal list tile"), onTap: () {}),
                ExpansionTile(
                  initiallyExpanded: _expanded,
                  title: Text("Sample expansion tile"),
                  trailing: SizedBox(
                    width: 48,
                    child: NxIcon(path: _expanded ? NxIcon.up : NxIcon.down),
                  ),
                  onExpansionChanged: (value) {
                    setState(() => _expanded = value);
                  },
                  children: [
                    ListTile(
                      title: Text("Transparent tile"),
                      tileColor: Colors.transparent,
                    ),
                  ],
                ),
                ListTile(
                  title: Text("Checkbox list tile: checked"),
                  trailing: Checkbox(value: true, onChanged: (value) {}),
                  onTap: () {},
                ),
                ListTile(
                  title: Text("Checkbox list tile: unchecked"),
                  trailing: Checkbox(value: false, onChanged: (value) {}),
                  onTap: () {},
                ),
                ListTile(
                  title: Text("Switch list tile: on"),
                  trailing: Switch(value: true, onChanged: (value) {}),
                  onTap: () {},
                ),
                ListTile(
                  title: Text("Switch list tile: off"),
                  trailing: Switch(value: false, onChanged: (value) {}),
                  onTap: () {},
                ),
                ListTile(
                  title: Text("Radio list tile: on"),
                  trailing: Radio(value: true, onChanged: (value) {}),
                  onTap: () {},
                ),
                ListTile(
                  title: Text("Radio list tile: off"),
                  trailing: Radio(value: false, onChanged: (value) {}),
                  onTap: () {},
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
