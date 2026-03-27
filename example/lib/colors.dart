import "package:flutter/material.dart";
import "package:nxdesign/colors.dart";
import "package:nxdesign/metrics.dart";

class ColorsDisplayScreen extends StatelessWidget {
  const ColorsDisplayScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("NxDesign Colors")),
      body: SafeArea(
        child: SingleChildScrollView(
          child: Padding(
            padding: const EdgeInsets.all(24),
            child: Column(
              spacing: 4,
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                Container(
                  height: 96,
                  decoration: const BoxDecoration(
                    borderRadius: NxMetrics.cardBorderRadius,
                    color: NxColors.nothingRed,
                  ),
                  child: const Center(child: Text("NxColors.nothingRed")),
                ),
                Container(
                  height: 96,
                  decoration: const BoxDecoration(
                    borderRadius: NxMetrics.cardBorderRadius,
                    color: NxColors.nothingYellow,
                  ),
                  child: const Center(
                    child: Text(
                      "NxColors.nothingYellow",
                      style: TextStyle(color: NxColors.lightThemeText),
                    ),
                  ),
                ),
                DecoratedBox(
                  decoration: const BoxDecoration(
                    borderRadius: NxMetrics.cardBorderRadius,
                    color: NxColors.lightThemeBackground,
                    border: Border.fromBorderSide(
                      BorderSide(color: NxColors.lightInactive),
                    ),
                  ),
                  child: Padding(
                    padding: const EdgeInsets.all(24),
                    child: Column(
                      spacing: 8,
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        const Text(
                          "Light Theme Colors",
                          style: TextStyle(color: NxColors.lightThemeText),
                        ),
                        const Divider(),
                        const SizedBox(
                          height: 64,
                          child: Center(
                            child: Text(
                              "NxColors.lightThemeBackground",
                              style: TextStyle(color: NxColors.lightThemeText),
                            ),
                          ),
                        ),
                        Container(
                          height: 64,
                          decoration: BoxDecoration(
                            borderRadius: NxMetrics.cardBorderRadius.subtract(
                              NxMetrics.defaultBorderRadius,
                            ),
                            color: NxColors.lightThemeCard,
                          ),
                          child: const Center(
                            child: Text(
                              "NxColors.lightThemeCard",
                              style: TextStyle(color: NxColors.lightThemeText),
                            ),
                          ),
                        ),
                        Container(
                          height: 64,
                          decoration: BoxDecoration(
                            borderRadius: NxMetrics.cardBorderRadius.subtract(
                              NxMetrics.defaultBorderRadius,
                            ),
                            color: NxColors.lightThemeListItem,
                          ),
                          child: const Center(
                            child: Text(
                              "NxColors.lightThemeListItem",
                              style: TextStyle(color: NxColors.lightThemeText),
                            ),
                          ),
                        ),
                        Container(
                          height: 64,
                          decoration: BoxDecoration(
                            borderRadius: NxMetrics.cardBorderRadius.subtract(
                              NxMetrics.defaultBorderRadius,
                            ),
                            color: NxColors.lightInactive,
                          ),
                          child: const Center(
                            child: Text(
                              "NxColors.lightInactive",
                              style: TextStyle(color: NxColors.lightThemeText),
                            ),
                          ),
                        ),
                        Container(
                          height: 64,
                          decoration: BoxDecoration(
                            borderRadius: NxMetrics.cardBorderRadius.subtract(
                              NxMetrics.defaultBorderRadius,
                            ),
                            color: NxColors.lightThemeText,
                          ),
                          child: const Center(
                            child: Text(
                              "NxColors.lightThemeText",
                              style: TextStyle(color: NxColors.darkThemeText),
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
                DecoratedBox(
                  decoration: const BoxDecoration(
                    borderRadius: NxMetrics.cardBorderRadius,
                    color: NxColors.darkThemeBackground,
                    border: Border.fromBorderSide(
                      BorderSide(color: NxColors.darkInactive),
                    ),
                  ),
                  child: Padding(
                    padding: const EdgeInsets.all(24),
                    child: Column(
                      spacing: 8,
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        const Text(
                          "Dark Theme Colors",
                          style: TextStyle(color: NxColors.darkThemeText),
                        ),
                        const Divider(),
                        const SizedBox(
                          height: 64,
                          child: Center(
                            child: Text(
                              "NxColors.darkThemeBackground",
                              style: TextStyle(color: NxColors.darkThemeText),
                            ),
                          ),
                        ),
                        Container(
                          height: 64,
                          decoration: BoxDecoration(
                            borderRadius: NxMetrics.cardBorderRadius.subtract(
                              NxMetrics.defaultBorderRadius,
                            ),
                            color: NxColors.darkThemeCard,
                          ),
                          child: const Center(
                            child: Text(
                              "NxColors.darkThemeCard",
                              style: TextStyle(color: NxColors.darkThemeText),
                            ),
                          ),
                        ),
                        Container(
                          height: 64,
                          decoration: BoxDecoration(
                            borderRadius: NxMetrics.cardBorderRadius.subtract(
                              NxMetrics.defaultBorderRadius,
                            ),
                            color: NxColors.darkThemeListItem,
                          ),
                          child: const Center(
                            child: Text(
                              "NxColors.darkThemeListItem",
                              style: TextStyle(color: NxColors.darkThemeText),
                            ),
                          ),
                        ),
                        Container(
                          height: 64,
                          decoration: BoxDecoration(
                            borderRadius: NxMetrics.cardBorderRadius.subtract(
                              NxMetrics.defaultBorderRadius,
                            ),
                            color: NxColors.darkInactive,
                          ),
                          child: const Center(
                            child: Text(
                              "NxColors.darkInactive",
                              style: TextStyle(color: NxColors.darkThemeText),
                            ),
                          ),
                        ),
                        Container(
                          height: 64,
                          decoration: BoxDecoration(
                            borderRadius: NxMetrics.cardBorderRadius.subtract(
                              NxMetrics.defaultBorderRadius,
                            ),
                            color: NxColors.darkThemeText,
                          ),
                          child: const Center(
                            child: Text(
                              "NxColors.darkThemeText",
                              style: TextStyle(color: NxColors.lightThemeText),
                            ),
                          ),
                        ),
                      ],
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
