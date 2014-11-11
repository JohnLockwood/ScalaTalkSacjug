package com.codesolid.sacjug

import org.scalatest._

/**
 * Created by john on 11/11/14.
 */
abstract class UnitSpec extends FlatSpec with Matchers with
    OptionValues with Inside with Inspectors {
}
