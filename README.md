# MusicMaker
A simple program to make music

## Creating an instrument track
Create a text file. 
Each line represents that instrument played at a certain pitch. 
Define the pitch at the start of the line by entering three characters. The program can read two ways of writing notes:
    You can enter a two digit MIDI number and a space. For example, "60 " or " 60" becomes middle C.
    You can enter a letter representing a note; a character representing a sharp, flat, or natural; and finally a number representing the MIDI octave. For example, "C 4" becomes middle C, "CF4" becomes a C flat (or just a B), and "CS4" becomes a C sharp. Any second character other than F or S is read as a natural.
Vertical bars can be used as visual dividers, to break up the measures, the code will ignore them.

Legend:
- Place an X to play a note
- Place a space to avoid playing anything at all

Example:  

Jingle Bells:
<pre>
60 |    |    |  X |    |    |    |    |    |    |    |  X |    |    |    |    |X   |  
62 |    |    |   X|    |    |    | XX |X   |    |    |   X|    |    |    |   X|    |  
E 4|XXX |XXX |X   |X   |    | XXX|X  X|    |XXX |XXX |X   |X   |    | XXX|    |    |  
ES4|    |    |    |    |XXXX|X   |    |    |    |    |    |    |XXXX|X   |  X |    |  
67 |    |    | X  |    |    |    |    |  X |    |    | X  |    |    |    |XX  |    |  
69 |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |  
71 |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |  
72 |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |  X |  
</pre>