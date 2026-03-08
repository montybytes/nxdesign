import 'package:flutter/material.dart' hide Checkbox, Switch, Radio;
import 'package:nxdesign/themes.dart';
import 'package:nxdesign/widgets.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

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
                  initiallyExpanded: true,
                  title: Text("Sample expansion tile"),
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
