package i4s.opencv.image.constants

object ConnectedComponentsAlgorithmsTypes extends Enumeration {
  type ConnectedComponentsAlgorithmsType = Value

  /** Spaghetti \cite Bolelli2019 algorithm for 8-way connectivity, Spaghetti4C \cite Bolelli2021 algorithm for 4-way connectivity. */
  val Default: Value = Value(-1)

  /** SAUF \cite Wu2009 algorithm for 8-way connectivity, SAUF algorithm for 4-way connectivity. The parallel implementation described in \cite Bolelli2017 is available for SAUF. */
  val Wu: Value= Value(0)

  /** BBDT \cite Grana2010 algorithm for 8-way connectivity, SAUF algorithm for 4-way connectivity. The parallel implementation described in \cite Bolelli2017 is available for both BBDT and SAUF. */
  val Grana: Value = Value(1)

  /** Spaghetti \cite Bolelli2019 algorithm for 8-way connectivity, Spaghetti4C \cite Bolelli2021 algorithm for 4-way connectivity. The parallel implementation described in \cite Bolelli2017 is available for both Spaghetti and Spaghetti4C. */
  val Bolelli: Value = Value(2)

  /** Same as CCL_WU. It is preferable to use the flag with the name of the algorithm (CCL_SAUF) rather than the one with the name of the first author (CCL_WU). */
  val Sauf: Value = Value(3)

  /** Same as CCL_GRANA. It is preferable to use the flag with the name of the algorithm (CCL_BBDT) rather than the one with the name of the first author (CCL_GRANA). */
  val BBDT: Value = Value(4)

  /** Same as CCL_BOLELLI. It is preferable to use the flag with the name of the algorithm (CCL_SPAGHETTI) rather than the one with the name of the first author (CCL_BOLELLI). */
  val Spaghetti: Value = Value(5)

}
