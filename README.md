# minesweeper
My playable Java implementation of Minesweeper.

```
************************** Welcome to Minesweeper! **************************
Enter your moves as the letter and number corresponding to each cell (ex. C5).

Please enter a board size between 8 and 26: 8

    1 2 3 4 5 6 7 8
   ----------------
A |                
B |                
C |                
D |                
E |                
F |                
G |                
H |                

Please enter your move: A1

    1 2 3 4 5 6 7 8
   ----------------
A | - - - 1        
B | 1 1 - 1        
C |     1          
D |                
E |                
F |                
G |                
H |              

.
.
.
    1 2 3 4 5 6 7 8
   ----------------
A | - - - 1       1
B | 1 1 - 1        
C |   2 1 2       1
D | 2   1       1 -
E | 1 1       2 - -
F | - - 1     2 - -
G | - - 1       1 -
H | - - - 1     1 -

Please enter your move: D4

.
.
.
    1 2 3 4 5 6 7 8
   ----------------
A | - - - 1 1 2 1 1
B | 1 1 - 1 * 3 * 1
C | * 2 1 2 3 * 2 1
D | 2 * 1 2 * 3 1 -
E | 1 1 2 3 * 2 - -
F | - - 1 * 3 2 - -
G | - - 1 2 * 2 1 -
H | - - - 1 2 * 1 -

Congratulations, you won!
Play again? (y/n): n
Thanks for playing!
```
