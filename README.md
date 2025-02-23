# My Personal Project

## Music tuner and song composer in Java
The application will work as a music tuner, given a sound input (initially modeled as a frequency value on
console input), and will also allow the user to compose music and play it back later. 

USER NOTE: Avoid using octaves lower than 4 or higher than 6 if you want a nice looking image of the music, at 
higher and lower octaves other than 5 the notes can go off the image. 

## User Stories
### REALIZED USER STORIES:
- As a user, I want to be able to compose music pieces of varying lengths of single notes only.
(arbitrary number of notes, added to a piece)
- As a user, I want to be able to play back composed pieces and see the piece on screen.
- As a user, I want to be able to view the progress of my piece as I compose it. 
- As a user, I want to be able to see how long each piece composed is.
- As a user, I want to be able to go back to pieces that I have composed earlier within the session and edit or modify
- those pieces. 
### User stories related to data persistence:
- As a user, I want to be able to save composed pieces to a memory for playback later whenever I want.
- As a user, I want to be able to go back to pieces I have composed earlier and load them from memory to edit, modify,
- and/or playback, whenever I want. 

### Not yet realized user stories:
- As a user, I want to be able to compose music with notes with different instrument sounds in the same piece.
 

## Stretch goals:
- Tuner program: (More complex because requires Fourier Transforms to read frequencies)
- As a user, I want to be able to tune my instruments to the correct pitch using the middle C scale of notes.
- As a user, I want to be able to view the history of the notes I have tuned.

- Minigame:
- Potentially add a minigame where the user will need to playback a series of notes, with a few select
- difficulties. Correctly playing back the notes will earn points which will be displayed at the end of the game.
- Game will end once X amount of notes are successfully played back or Y amount of wrong notes are played back.

## Instructions for Grader:
- First required action related to adding X's to Y: (Add X to Y button)
Select a piece from the dropdown box at the center of the main frame, click select, then choose edit piece
or create new piece by clicking add piece from button at menu bar at top under Piece and enter a name for the new piece.
- This opens up a new frame where you can create a Note and then add it to the piece by clicking Add Note.
- This adds an X (Note) to a Y (Piece).

- Second required action related to adding X's to Y: (Delete X from Y buttons)
To delete a note from the piece either select Delete last added note which deletes the last indexed note or select
Delete Note at Index which prompts for an index and deletes the note at that index. 

- Finding the Visual component:
The visual component can be found by selecting a piece from the dropdown and clicking select, then choose Play Piece.
After piece is done playing the visual component will pop up in a new frame. It will then also be saved as a jpg file
in the data folder with the same file name as the piece name. 

- Saving and loading state of the application:
The user can save and load any time by clicking the save and load options under File at the menu bar on the top. 
Saving can also be done by pressing X on the main frame and choosing Yes when prompted to save before exiting. 


## PHASE 4 TASK 2:
Representative sample of events occuring in the program. (Start program, auto loads saved memory (blank in this case)
piece named Test created, notes are added and deleted, piece contents viewed, memory saved, memory cleared, 
memory loaded, then piece is removed from memory)

Tue Apr 11 02:06:01 PDT 2023
Saved memory loaded.

Tue Apr 11 02:06:11 PDT 2023
Piece Test added to local memory

Tue Apr 11 02:06:19 PDT 2023
Note C5/0.25  added to piece Test

Tue Apr 11 02:06:22 PDT 2023
Note D5/0.25  added to piece Test

Tue Apr 11 02:06:23 PDT 2023
Note E5/0.25  added to piece Test

Tue Apr 11 02:06:25 PDT 2023
Note F5/0.25  added to piece Test

Tue Apr 11 02:06:26 PDT 2023
Note G5/0.25  added to piece Test

Tue Apr 11 02:06:27 PDT 2023
Note G5/0.25  removed from piece Test

Tue Apr 11 02:06:31 PDT 2023
Note C5/0.25  removed from piece Test

Tue Apr 11 02:06:37 PDT 2023
Showing contents of piece Test

Tue Apr 11 02:06:48 PDT 2023
Memory saved to JSON file.

Tue Apr 11 02:06:52 PDT 2023
Local memory cleared!

Tue Apr 11 02:06:55 PDT 2023
Saved memory loaded.

Tue Apr 11 02:06:55 PDT 2023
Note D5/0.25  added to piece Test

Tue Apr 11 02:06:55 PDT 2023
Note E5/0.25  added to piece Test

Tue Apr 11 02:06:55 PDT 2023
Note F5/0.25  added to piece Test

Tue Apr 11 02:06:55 PDT 2023
Piece Test added to local memory

Tue Apr 11 02:07:06 PDT 2023
Piece Test removed from local memory


## PHASE 4 TASK 3:
I would split my current ComposerGUI class into one other class that handles constructing the GUI layout for the main
JFrame, and let ComposerGUI handle only the initialization and closing of the entire GUI. According to the Single
Responsibility Principle we want each class to only be centered around one cohesive concept, and currently
my ComposerGUI has two clusters of methods, one set for initialization and closing of the GUI and one for creating the 
GUI layout. Advantages of splitting this class would be to adhere to the SRP better, and to help in debugging when
problems arise in the future as I develop and update the code. Having these concepts separated into separate classes
will make it easier to see exactly where a bug is arising. 

I would also refactor the more complex if else code blocks in some of my methods and classes like the ClickHandler
class inside the SimplePianoGUI class to use switch cases instead. This would make the code much more streamlined than
it currently is, improving readability, and slightly improve runtime since the compiler no longer has to check all the 
if else cases starting from the top and instead go directly to the corresponding switch case. 
However a switch case block would cause the method length to go beyond the line limit checkstyle, but since this
is a justified reason for doing so I feel it is worth refactoring into a switch statement. 