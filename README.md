# MusicMaker
A simple program to make music

## Creating an instrument track
Create a text file. 
Each line represents that instrument played at a certain pitch. 
Define the pitch at the start of the line by entering in a two-digit number between 00 and 99, with middle C being 60.
Vertical bars can be used as visual dividers, to break up the measures, the code will ignore them.

Legend:
- Place an X to play a note
- Place a space to avoid playing anything at all

Example:

<pre>
Jingle bells:  
60|    |    |  X |    |    |    |    |    |    |    |  X |    |    |    |    |X   |  
62|    |    |   X|    |    |    | XX |X   |    |    |   X|    |    |    |   X|    |  
64|XXX |XXX |X   |X   |    | XXX|X  X|    |XXX |XXX |X   |X   |    | XXX|    |    |  
65|    |    |    |    |XXXX|X   |    |    |    |    |    |    |XXXX|X   |  X |    |  
67|    |    | X  |    |    |    |    |  X |    |    | X  |    |    |    |XX  |    |  
69|    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |  
71|    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |  
72|    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |  X |  
</pre>