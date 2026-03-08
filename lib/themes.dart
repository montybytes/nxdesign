import "package:flutter/material.dart";
import "package:nxdesign/colors.dart";
import "package:nxdesign/fonts.dart";
import "package:nxdesign/metrics.dart";

class NxTheme {
  static const _defaultTooltipTheme = TooltipThemeData();

  static const _textInputDefaultDecoration = InputDecorationThemeData(
    filled: true,
    border: OutlineInputBorder(
      borderRadius: NxMetrics.largeBorderRadius,
      borderSide: BorderSide(color: Colors.transparent),
    ),
    enabledBorder: OutlineInputBorder(
      borderRadius: NxMetrics.largeBorderRadius,
      borderSide: BorderSide(color: Colors.transparent),
    ),
    focusedBorder: OutlineInputBorder(
      borderRadius: NxMetrics.largeBorderRadius,
      borderSide: BorderSide(color: NxColors.nothingRed),
    ),
  );

  static const _timePickerDefaultTheme = TimePickerThemeData(
    hourMinuteShape: RoundedRectangleBorder(
      borderRadius: NxMetrics.largeBorderRadius,
    ),
    dayPeriodShape: RoundedRectangleBorder(
      borderRadius: NxMetrics.largeBorderRadius,
    ),
  );

  static const _textSelectionDefaultTheme = TextSelectionThemeData(
    cursorColor: NxColors.nothingRed,
    selectionColor: NxColors.nothingRed,
    selectionHandleColor: NxColors.nothingRed,
  );

  static const _expansionTileDefaultTheme = ExpansionTileThemeData(
    shape: RoundedRectangleBorder(borderRadius: NxMetrics.largeBorderRadius),
    collapsedShape: RoundedRectangleBorder(
      borderRadius: NxMetrics.largeBorderRadius,
    ),
  );

  static const _listTileDefaultTheme = ListTileThemeData(
    shape: RoundedRectangleBorder(borderRadius: NxMetrics.largeBorderRadius),
    contentPadding: EdgeInsets.symmetric(horizontal: 16),
  );

  static const _cardDefaultTheme = CardThemeData(
    elevation: 0,
    clipBehavior: Clip.antiAlias,
    margin: EdgeInsets.zero,
  );

  static const _appBarDefaultTheme = AppBarThemeData(
    elevation: 0,
    scrolledUnderElevation: 0,
  );

  static final lightTheme = ThemeData(
    package: "nxdesign",
    useMaterial3: true,
    brightness: Brightness.light,
    fontFamily: NxFonts.defaultFontFamily,
    canvasColor: NxColors.lightThemeBackground,
    scaffoldBackgroundColor: NxColors.lightThemeBackground,
    dividerColor: NxColors.lightInactive,
    textTheme: ThemeData().textTheme.apply(
      bodyColor: NxColors.lightThemeText,
      displayColor: NxColors.lightThemeText,
      decorationColor: NxColors.lightThemeText,
      fontFamily: NxFonts.defaultFontFamily,
    ),
    textSelectionTheme: _textSelectionDefaultTheme,
    inputDecorationTheme: _textInputDefaultDecoration.copyWith(
      fillColor: NxColors.lightThemeListItem,
    ),
    cardTheme: _cardDefaultTheme.copyWith(
      color: NxColors.lightThemeCard,
      shape: RoundedRectangleBorder(borderRadius: NxMetrics.cardBorderRadius),
    ),
    iconTheme: const IconThemeData(color: NxColors.lightThemeText),
    tooltipTheme: _defaultTooltipTheme.copyWith(
      textStyle: const TextStyle(color: NxColors.lightThemeText),
      decoration: const BoxDecoration(
        color: NxColors.lightThemeCard,
        borderRadius: NxMetrics.largeBorderRadius,
      ),
    ),
    appBarTheme: _appBarDefaultTheme.copyWith(
      iconTheme: const IconThemeData(color: NxColors.lightThemeText),
      backgroundColor: NxColors.lightThemeBackground,
      foregroundColor: NxColors.lightThemeText,
    ),
    listTileTheme: _listTileDefaultTheme.copyWith(
      textColor: NxColors.lightThemeText,
      tileColor: NxColors.lightThemeListItem,
    ),
    expansionTileTheme: _expansionTileDefaultTheme.copyWith(
      textColor: NxColors.lightThemeText,
      iconColor: NxColors.lightThemeText,
      backgroundColor: NxColors.lightThemeListItem,
      collapsedTextColor: NxColors.lightThemeText,
      collapsedIconColor: NxColors.lightThemeText,
      collapsedBackgroundColor: NxColors.lightThemeListItem,
    ),
    dialogTheme: const DialogThemeData(
      backgroundColor: NxColors.lightThemeCard,
    ),
    timePickerTheme: _timePickerDefaultTheme.copyWith(
      dialHandColor: NxColors.nothingRed,
      dialTextColor: NxColors.lightThemeText,
      dialBackgroundColor: NxColors.lightThemeListItem,
      dayPeriodColor: NxColors.nothingRed,
      dayPeriodTextColor: NxColors.lightThemeText,
      hourMinuteColor: NxColors.lightThemeListItem,
      hourMinuteTextColor: NxColors.lightThemeText,
      backgroundColor: NxColors.lightThemeCard,
      dayPeriodBorderSide: const BorderSide(color: NxColors.lightThemeListItem),
    ),
  );

  static final darkTheme = ThemeData(
    package: "nxdesign",
    useMaterial3: true,
    brightness: Brightness.dark,
    fontFamily: NxFonts.defaultFontFamily,
    canvasColor: NxColors.darkThemeBackground,
    scaffoldBackgroundColor: NxColors.darkThemeBackground,
    dividerColor: NxColors.darkInactive,
    textTheme: ThemeData().textTheme.apply(
      bodyColor: NxColors.darkThemeText,
      displayColor: NxColors.darkThemeText,
      decorationColor: NxColors.darkThemeText,
      fontFamily: NxFonts.defaultFontFamily,
    ),
    textSelectionTheme: _textSelectionDefaultTheme,
    inputDecorationTheme: _textInputDefaultDecoration.copyWith(
      fillColor: NxColors.darkThemeListItem,
    ),
    cardTheme: _cardDefaultTheme.copyWith(
      color: NxColors.darkThemeCard,
      shape: RoundedRectangleBorder(borderRadius: NxMetrics.cardBorderRadius),
    ),
    iconTheme: const IconThemeData(color: NxColors.darkThemeText),
    tooltipTheme: _defaultTooltipTheme.copyWith(
      textStyle: const TextStyle(color: NxColors.darkThemeText),
      decoration: const BoxDecoration(
        color: NxColors.darkThemeCard,
        borderRadius: NxMetrics.largeBorderRadius,
      ),
    ),
    appBarTheme: _appBarDefaultTheme.copyWith(
      iconTheme: const IconThemeData(color: NxColors.darkThemeText),
      backgroundColor: NxColors.darkThemeBackground,
      foregroundColor: NxColors.darkThemeText,
    ),
    listTileTheme: _listTileDefaultTheme.copyWith(
      textColor: NxColors.darkThemeText,
      tileColor: NxColors.darkThemeListItem,
    ),
    expansionTileTheme: _expansionTileDefaultTheme.copyWith(
      textColor: NxColors.darkThemeText,
      iconColor: NxColors.darkThemeText,
      backgroundColor: NxColors.darkThemeListItem,
      collapsedTextColor: NxColors.darkThemeText,
      collapsedIconColor: NxColors.darkThemeText,
      collapsedBackgroundColor: NxColors.darkThemeListItem,
    ),
    dialogTheme: const DialogThemeData(backgroundColor: NxColors.darkThemeCard),
    timePickerTheme: _timePickerDefaultTheme.copyWith(
      dialHandColor: NxColors.nothingRed,
      dialTextColor: NxColors.darkThemeText,
      dialBackgroundColor: NxColors.darkThemeListItem,
      dayPeriodColor: NxColors.nothingRed,
      dayPeriodTextColor: NxColors.darkThemeText,
      hourMinuteColor: NxColors.darkThemeListItem,
      hourMinuteTextColor: NxColors.darkThemeText,
      backgroundColor: NxColors.darkThemeCard,
      dayPeriodBorderSide: const BorderSide(color: NxColors.darkThemeListItem),
    ),
  );
}
