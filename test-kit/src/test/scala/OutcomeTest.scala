package chess

import munit.ScalaCheckSuite

class OutcomeTest extends ScalaCheckSuite:

  import Outcome.*

  test("standard outcomes"):
    assertEquals(fromResult("1-0"), Some(white))
    assertEquals(fromResult("0-1"), Some(black))
    assertEquals(fromResult("1/2-1/2"), Some(draw))
    assertEquals(fromResult("0.5-0.5"), Some(draw))
    assertEquals(fromResult(""), None)
    assertEquals(fromResult("*"), None)

  test("silly format outcomes"):
    assertEquals(fromResult("+_-"), Some(white))
    assertEquals(fromResult("0:1"), Some(black))
    assertEquals(fromResult("½-½"), Some(draw))
    assertEquals(fromResult("WHITEWIN"), Some(white))
    assertEquals(fromResult("DRAW"), Some(draw))

  test("points"):
    def normalize(s: String) = showPoints(pointsFromResult(s))
    assertEquals(normalize("1-0"), "1-0")
    assertEquals(normalize("0-1"), "0-1")
    assertEquals(normalize("—:—"), "0-0")
    assertEquals(normalize("1/2_0"), "1/2-0")
    assertEquals(normalize("0-½"), "0-1/2")
    assertEquals(normalize("0.5-0"), "1/2-0")
    assertEquals(normalize("*"), "*")
