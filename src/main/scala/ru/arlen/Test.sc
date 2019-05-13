import ru.arlen.typeclass.implic.eq._
import ru.arlen.typeclass.implic.ord._
import ru.arlen.typeclass._

Ratio(1, 2) === Ratio(2, 4)
Ratio(1, 2) === Ratio(2, 4)
Ratio(1, 2) compare Ratio(3, 4)
Ratio2(1, 2) === Ratio2(2, 4)
Eq.compareList(Seq(Ratio(1, 2), Ratio(3, 6)), Seq(Ratio(2, 4), Ratio(3, 6)))
Seq(Ratio(1, 2), Ratio(3, 6)) === Seq(Ratio(2, 4), Ratio(3, 6))

SemiGroup.combineList(List("aa", "bb", "cc"))
SemiGroup.combineList(List(Ratio(1,2),Ratio(1,2)))
SemiGroup.combineList(List(Ratio(1,2),Ratio(1,2)).map(Sum(_))).map(_.x)
SemiGroup.combineList(List(Ratio(1,2),Ratio(1,2)).map(Prod(_))).map(_.x)
//SemiGroup.combineListVia[Sum[Ratio], Ratio](List(Ratio(1,2),Ratio(1,2)))
SemiGroup.combineListVia[Sum](List(Ratio(1,2),Ratio(1,2)))
SemiGroup.combineListVia[Sum]((1 to 10).toList)