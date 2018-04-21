# minesweeper
My playable Java implementation of minesweeper.

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
B | - - - 2        
C | - - - 1        
D | - - 1          
E | 1 1            
F |                
G |                
H |      

.
.
.
    1 2 3 4 5 6 7 8
   ----------------
A | - 1   1        
B | 1 2 2 1 1      
C | 1   1 - 2 3    
D | 1 1 1 - 1      
E | 1 1 1 - 1 2    
F | 1   1 - - 1 1 1
G | 2 2 1 - - - - -
H |   1 - - - - - -

Please enter your move: A5

.
.
.
    1 2 3 4 5 6 7 8
   ----------------
A | - 1 * 1 1 1 3 *
B | 1 2 2 1 1 * 4 *
C | 1 * 1 - 2 3 * 2
D | 1 1 1 - 1 * 3 2
E | 1 1 1 - 1 2 * 1
F | 1 * 1 - - 1 1 1
G | 2 2 1 - - - - -
H | * 1 - - - - - -

Congratulations, you won!
Play again? (y/n): n
Thanks for playing!
```
