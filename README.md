# Game of Life
[![Build Status](https://travis-ci.org/JoshTumath/bbcgradscheme-gameoflife.svg?branch=master)](https://travis-ci.org/JoshTumath/bbcgradscheme-gameoflife)

This is an implementation of
[Conway's Game of Life](https://en.wikipedia.org/wiki/Conway's_Game_of_Life) in
Java. This was created in preparation for an Assessment Centre at the BBC.

In this particular implementation, no assumption about the size of the grid is
made, so both positive and negative coordinates are permitted for the cells of
the grid. Additionally, I assumed that it was only intended for me to create
tests for the game, and there was no need for me to create a view of the grid
either in a CLI or GUI.

To run tests for the Game of Life, use the following command:

```
ant test
```