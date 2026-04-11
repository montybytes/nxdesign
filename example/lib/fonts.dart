import "package:flutter/material.dart" hide AppBar;
import "package:nxdesign/fonts.dart";
import "package:nxdesign/widgets.dart";

class FontsDisplayScreen extends StatelessWidget {
  const FontsDisplayScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      appBar: PreferredSize(
        preferredSize: Size.fromHeight(kToolbarHeight),
        child: AppBar(
          title: "NxDesign Colors",
          titleStyle: TextStyle(fontSize: 32, fontFamily: NxFonts.fontNType),
        ),
      ),
      body: SafeArea(
        child: SingleChildScrollView(
          child: Padding(
            padding: EdgeInsets.all(24),
            child: Column(
              spacing: 4,
              children: [
                ListTile(
                  title: Text(
                    "NType",
                    style: TextStyle(
                      fontSize: 32,
                      fontFamily: NxFonts.fontNType,
                    ),
                  ),
                  subtitle: Text("Usage: NxFonts.fontNType"),
                ),
                ListTile(
                  title: Text(
                    "NDot",
                    style: TextStyle(
                      fontSize: 32,
                      fontFamily: NxFonts.fontNDot,
                    ),
                  ),
                  subtitle: Text("Usage: NxFonts.fontNDot"),
                ),
                ListTile(
                  title: Text(
                    "LetteraMono",
                    style: TextStyle(
                      fontSize: 32,
                      fontFamily: NxFonts.fontLettera,
                    ),
                  ),
                  subtitle: Text("Usage: NxFonts.fontLettera"),
                ),
                ListTile(
                  title: Text(
                    "Inter",
                    style: TextStyle(
                      fontSize: 32,
                      fontFamily: NxFonts.fontInter,
                    ),
                  ),
                  subtitle: Text("Usage: NxFonts.fontInter"),
                ),
                ListTile(
                  title: Text(
                    "SpaceGrotesk",
                    style: TextStyle(
                      fontSize: 32,
                      fontFamily: NxFonts.fontSpaceGrotesk,
                    ),
                  ),
                  subtitle: Text("Usage: NxFonts.fontSpaceGrotesk"),
                ),
                ListTile(
                  title: Text(
                    "SpaceMono",
                    style: TextStyle(
                      fontSize: 32,
                      fontFamily: NxFonts.fontSpaceMono,
                    ),
                  ),
                  subtitle: Text("Usage: NxFonts.fontSpaceMono"),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
