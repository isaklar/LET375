------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE:
PURPOSE OF PROJECT:
VERSION or DATE:
HOW TO START THIS PROJECT:
AUTHORS:
USER INSTRUCTIONS:


N = 10: Alla resultat är samma med en tid på 0 microsekunder vilket antyder på
att uträkningarna inte är stora nog för att visa en tydlig skillnad.
Algorithm #3	N = 10, time = 0 microsec
Algorithm #4	N = 10, time = 0 microsec
Algorithm #2	N = 10, time = 0 microsec
Algorithm #1	N = 10, time = 0 microsec

N = 100: Är också ganska ointressant eftersom vi fortfarande inte kan se ngt
tydligt mönster
Algorithm #3	N = 100, time = 2 microsec
Algorithm #4	N = 100, time = 4 microsec
Algorithm #2	N = 100, time = 5 microsec
Algorithm #1	N = 100, time = 98 microsec

N = 1000: Nu börjar vi se resultat. Det är nu vi börjar se hur stora skillnaderna
kan bli beroende på algoritm.
Algorithm #3	N = 1000, time = 21 microsec
Algorithm #4	N = 1000, time = 53 microsec
Algorithm #2	N = 1000, time = 335 microsec
Algorithm #1	N = 1000, time = 78000 microsec

#3: 214/21 = 10.19
#4: 577/53 = 10.88
#2: Ser ut att växa 10^2 gånger(335 * 10^2 = ~33500) => N^2
#1: Ser ut att växa 10^3 gånger(78000 * 10^3 = ~78000000) => N^3
Algorithm #3	N = 10000, time = 214 microsec
Algorithm #4	N = 10000, time = 577 microsec
Algorithm #2	N = 10000, time = 30150 microsec
Algorithm #1	N = 10000, time = 72399000 microsec

#3: 1895/214 = 8.885
#4: 6099/577 = 10.57
#2: Ser ut att fortfaranade vara runt 10^2 => N^2
#1: Blir bara väntetid och resulterar i att programmet hoppar över den.
Algorithm #3	N = 100000, time = 1895 microsec
Algorithm #4	N = 100000, time = 6099 microsec
Algorithm #2	N = 100000, time = 2658000 microsec

#3: 19037/1895 = 10.04
#4: 63187/6099 = 10.36
#2: Uträkningen blir för stor och slutar med oändlig(?) väntetid.
Algorithm #3	N = 1000000, time = 19037 microsec
Algorithm #4	N = 1000000, time = 63187 microsec
