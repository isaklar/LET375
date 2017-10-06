Laboration 3
Gabriel Lindeby Isak Einler Larsson
gablinde@student.chalmers.se, isaklar@student.chalmers.se

toString:
  går igenom den länkade listan och lägger lägger till inehållet i
  element till en sträng som sedan retuneras.

contains:
  går igenom den länkade listan och jämför element i varje nod med c.
  Om c finns i listan retuneras true annars false.

copyUpperCase:
  skapar en ny länkad upperCaseList, går igenom den inkommande listan
  och Character.toUpperCase för att göra bokstaven stor och därefter
  används addLast metoden för att lägga till i upperCaseList som sedan
  retuneras.

addFirst:
  skapar en ny ListNode som blir head, därefter läggs c till i
  element på noden som tidigare var först i listan. Sedan länkas
  head.next till noden som tidigare var först i listan.

getLastNode:
  Kollar om den länkade listan är tom i så fall retuneras head noden.
  Om listan inte är tom loopas listan igenom tills next är null och den
  noden retuneras.

addLast:
  Hämtar sista noden med getLastNode och därefter skapar en ny nod som
  lastNode pekar på. där efter läggs c till i element på den sista noden.

concat:
  Hämtar sista elementet i l1 och gör så att den pekar på första elementet
  i l2 (altså inte head) därefter sätts l2.next till null.

addAll:
  utnyttjar copy och concat för att lägga ihop en kopia till l2 och l1

reverse:
  skapar en ny lista: reversed, går sedan igenom den inkommande listan och
  lägger till ellement från den först i reverse med metoden addFirst
  vilket resulterar i en omvänd lista.
