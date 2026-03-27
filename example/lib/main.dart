import "package:flutter/material.dart";
import "package:nxdesign/themes.dart";
import "package:nxdesign_example/home.dart";

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
      home: const HomeScreen(),
    );
  }
}
