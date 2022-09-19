package i4s.opencv.core.constants

object DecompositionMethods extends Enumeration {
  type DecompositionMethod = Value

  /** Gaussian elimination with the optimal pivot element chosen. */
  val Lu: Value = Value(0)

  /** singular value decomposition (SVD) method; the system can be over-defined and/or the matrix
   * src1 can be singular */
  val SVD: Value = Value(1)

  /** eigenvalue decomposition; the matrix src1 must be symmetrical */
  val Eigen: Value = Value(2)

  /** Cholesky <pre>LL&#94;T</pre> factorization; the matrix src1 must be symmetrical and positively
   * defined */
  val Cholesky: Value = Value(3)

  /** QR factorization; the system can be over-defined and/or the matrix src1 can be singular */
  val QR: Value = Value(4)

  /** while all the previous flags are mutually exclusive, this flag can be used together with
   * any of the previous; it means that the normal equations
   * <pre>\texttt{src1^T\cdot\texttt{src1}\cdot\texttt{dst}=\texttt{src1}^T\texttt{src2}</pre> are
   * solved instead of the original system
   * <pre>\texttt{src1\cdot\texttt{dst}=\texttt{src2}</pre>
   */
  val Normal: Value = Value(16)

}
