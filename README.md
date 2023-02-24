# My Personal Project

## Music tuner and song composer in Java
The application will work as a music tuner, given a sound input (initially modeled as a frequency value on
console input), and will also allow the user to compose music and play it back later. 

## User Stories

- As a user, I want to be able to tune my instruments to the correct pitch using the middle C scale of notes.
- As a user, I want to be able to view the history of the notes I have tuned. 
- As a user, I want to be able to compose music pieces of varying lengths of single notes only.
(arbitrary number of notes, added to a piece)
- As a user, I want to be able to save composed pieces to a memory for playback later. 
- As a user, I want to be able to see how long each piece composed is.
- As a user, I want to be able to edit and modify saved music pieces that I have already composed. 


- As a user, I want to be able to compose music with notes with different instrument sounds in the same piece.
- This last point WILL BE ADDED LAST, THINKING OF HOW TO BEST IMPLEMENT THIS 
- (EITHER SPECIFY INSTRUMENT IN NOTE CLASS (1)
- OR SPECIFY IT IN COMPOSER AND FIND A WAY TO MERGE IT INTO THE PIECE(2))
- 1 IS LESS ELEGANT BUT SIMPLER TO IMPLEMENT
- 2 IS MORE ELEGANT AND SIMPLIFIED BUT HARDER TO IMLEMENT

## Stretch goals:
- Potentially add a minigame where the user will need to playback a series of notes, with a few select
- difficulties. Correctly playing back the notes will earn points which will be displayed at the end of the game.
- Game will end once X amount of notes are successfully played back or Y amount of wrong notes are played back.
