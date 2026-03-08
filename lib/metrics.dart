import "package:flutter/widgets.dart";

class NxMetrics {
  static const defaultBorderRadius = BorderRadius.all(Radius.circular(4));

  static const largeBorderRadius = BorderRadius.all(Radius.circular(12));

  static const cardBorderRadius = BorderRadius.all(Radius.circular(24));

  static const startBorderRadius = BorderRadius.vertical(
    top: Radius.circular(12),
    bottom: Radius.circular(4),
  );

  static const endBorderRadius = BorderRadius.vertical(
    top: Radius.circular(4),
    bottom: Radius.circular(12),
  );
}
