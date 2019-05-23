package ru.arlen.scalacheck

import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, WordSpec}

class Tests extends WordSpec with Matchers with MockFactory {
  "Calling is empty" should {
    "return true for a list with 0 elements" in {
      List().isEmpty shouldBe true
    }
    "return false for a list with >0 elements" in {
      List(1).isEmpty shouldBe false
    }
  }

  "Calling head" should {
    "thrown an exception for an empty list" in {
      intercept[NoSuchElementException] {
        List[Int]().head
      }
    }
  }


  "Mocking a trait" should {
    trait TestTrait {
      def getInt: Int
    }

    "return trained value for head" in {
      val mockList = mock[TestTrait]
      (mockList.getInt _).expects().returns(1)

      mockList.getInt shouldBe 1
    }
  }

}
