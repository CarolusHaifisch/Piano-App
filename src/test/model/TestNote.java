package model;

import java.io.IOException;

class NoteTest {
try {
        composer.memSave(memory);
    } catch (
    IOException e) {  // This might not even be necessary because IOexceptions shouldn't be thrown
        // in normal circumstances.
        System.out.println("Did not save properly.");
    }
}