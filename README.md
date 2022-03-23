Hodoku is a solver/generator/trainer/analyzer for standard sudoku. It is written in
Java/Swing and should therefore run on any platform supported by Java (tested
on Windows and Linux - Ubuntu/GTK+-LAF). Since it is written in Java the
Java Runtime Environment (JRE) version 1.8 or higher must be installed on your computer
before you can run Hodoku. The JRE am be downloaded from
http://www.oracle.com/technetwork/java/javase/downloads/index.html

![screenshot](https://raw.githubusercontent.com/PseudoFish/Hodoku/master/showcase/showcase.png)

Available languages: English and German

Hodoku uses rather a lot of memory
(especially if you use the "Find all available steps" feature). The recommended way
to run Hodoku is:

	java -Xmx512m -jar Hodoku.jar

Note: the parameter "-Xmx" is specific to the JRE provided by Oracle. If you use a
different JRE, please look up the correct parameter for setting the maximum
heap size at startup.

Forked from PseudoFish's fork.


Wish List:
+ Ability to draw arrows or hard/dashed lines
+ Find out how to make an exe/installer

Change log
==========

Version 3.0.0-beta2
-------------------
+ Added a timer (it currently does not pause, should add that eventually)
+ Added option to have candidate coloring alternate colors with each mark. Useful for coloring and chaining.

Version 3.0.0-beta1
-------------------
+ Added a filter for cells that contain exactly 3 candidates (temporary ugly icon)
+ Added a toggle (t) for switching between coloring modes


Bug Fix:
+ Bottom coloring bar was previously completely broken, it now works as expected
+ Fixed a bug where the escape key would not clear the coloring/reset the mode.



Version 2.3.2 (WIP)
-------------------

Minor:
  - Replaced Active Cell's Remove Candidate functionality with Toggle Candidate.
    - This feature now works even if Show all Candidates is off.
  - Simplified the prompt when Show all Candidates is turned on.

Bug Fix:
  - Filter inverse now colors properly on bivalue cells.

Version 2.3.1 (2020-01-13)
--------------------------

Features:

  - Added Quick Browse in the File menu
    - Can manually paste a list of import lines
    - Can load a file containing a list of import lines
    - Allows to quickly browse/load puzzles into Hodoku
    - Each line must start with an import line
    - Notes to the right of the import lines are ignored

Version 2.3.0 (2020-01-10)
--------------------------

Features:

  - Added Reset Candidates under the Puzzle menu
  - Digit filter/highlight has a distinguished color with customization in config.
  - Bottom window status shows selector location as RyCx notation.

Minor:

  - Added color option to customize the new colors in the color palette.
  - New Puzzle automatically sets the mouse back to Default mouse mode.
  - Repurposed F10 to filter/highlight bivalue cells.

Bug Fixes:

  - Fixed active cell selection hide on delayed interval.
  - Fixed the JAR entry point, console commands should work again.
  - New game resets the cursor color preview.

Version 2.2.11 (2020-01-01)
---------------------------

Features:

  - Toggle color visibility. Now you can hide colors without clearing it to see filtering/highlighting!
  - Show Filters on Givens. When this option is enabled, givens/set cell values will also be highlighted. This feature is off by default but can be turned on in preferences->General->Show filter on givens.
  - Auto highlight. When a given/set cell is selected, like candidates/cells will be highlighted. This feature can be enabled in preferences->General->Auto highlight.

Bug Fix:

  - Selecting a primary color in Default Mouse Mode no longer generates a colored mouse.
  - Filter/Highlight bivalue cells did not work when Show filter on candidate was enabled.
  - Fixed an infinite loop bug related to inputting digits on a multi solution puzzle.

Version 2.2.10 (2019-12-24)
---------------------------

Minor:

  - Color palette 'R' button now clears all colors regardless of radio button selection.
  - Added tooltips for the color chooser component.
  - Added German translation for those new components.
  - Added 2 colors to the color palette (Cyan and Yellow) which is often used for AICs.
  - Selection head is now part of the cell selection.

Bug Fix:

  - Primary color now updates correctly when setting its color to a custom value.
  - Clear variable state when resetting/loading new puzzles.

Version 2.2.9 (2019-12-18)
--------------------------

Features:

  - Color Palette
    - Instead of having 2 color palettes, one for cells, one for candidates, we now only have 1. Radio buttons have been added to determine if we are using the default mouse mode, coloring cells or coloring candidates.
    - We now have a primary and secondary color. Their order can be switched by using the arrow button between both colors.
    - Clicking on a color from the color palette will set the primary color to this value.
    - Clicking on the primary or secondary color will spawn a color selection window for further customization of colors than what is provided by Hodoku's default color palette.
    - The 'R' button at the bottom left of the primary and secondary color resets the colors on the board. If Color Cells is selected, only cell colors will be cleared. If Color Candidates is selected, only candidate colors will be cleared. If Default Mouse is selected, all colors will be cleared.
    - Colors are no longer switched with Shift is held down. Pressing once on 'X' will switch the color. You no longer have to hold it down.

Minor:

  - Input digit on a cell with a user input value now overwrites it.
  - Cells now color even if you drag and release on the same cell.
  - Save Image default size changed from 400px to 800px

Bug Fixes:

  - The Modify Givens button is not active when the program loads, and Play Game is not clickable.
  - Right click a user input cell shows the right click menu with the option to delete value.

Version 2.2.8 (2019-12-01)
--------------------------

Features:

  - Puzzle -> Solution Count
    - It counts how many solutions a puzzle has with a cutoff limit of 1000.
  - Clear an entire color channel in either Cells or Candidates. This is done in the Active Cell panel by Ctrl + Left Click on a color in the color palette. Only this color will be cleared from the board.
  - Mouse click while coloring no longer selects a cell. I found it distracting, sometimes the selector color overlay made the cell's colors look off. It can still be moved with the arrow keys. Maybe I should re-enable it, but add a toggle feature to automatically hide the selector when entering coloring mode.

Minor:

  - File -> New Empty
    - It generates an empty board. This replaces File -> New Givens. This new layout is more conventional with other programs.
  - Edit -> Edit Givens
    - Renamed and relocated File -> Modify Givens. It did not make sense being under the File menu. Now it is more clear what it does.
  - Edit -> Play Game
    - Relocated from File -> Play Game. This belongs with Edit Givens since you use it to leave editor mode.

Bug Fixes:

  - Active Cell color would disappear when clicking outside the grid to hide the cursor selector.
  - Active Color preview defaulted to cell color preview.
  - Faulty transition from drag to click while Ctrl down leading to a cell being deselected.
  - Batch generate would sometimes crash due to a comparator bug in the XY-Chains.

Version 2.2.7 (2019-11-23)
--------------------------

Features:

  - The active cell selector has been made larger then the rest of the cell selection to distinguish it.
  - Cell selection is more responsive. Immediate selection on down event.
  - German translation support has been added.

Bug Fixes:

  - Active cell selector position now updates correctly on mouse drag.
  - "Solve up to" had a bug where it would go in an endless loop on a puzzle where the engine gives-up.
  - Cell selection bug when removing existing selection from previous selection.
  - Solve Puzzle now throws an appropriate message when the puzzle has multiple solutions.
  - Right click no longer clears the selection.
  - An empty config file is deleted and re-generated.
  - Internal code redesigning and refactoring.

Version 2.2.6 (2019-11-22)
--------------------------

Features:

  - The cell selector now wraps around the board instead of getting stuck at the edge.

Bug Fixes:

  - The Active Cell panel color selection has been made more responsive.
  - Disabled candidate highlight on LCtrl down because it was currently doing too many things. Until I find a better way to edit candidates and append cells selection, this feature will only be toggleable via the Options -> Show Candidate Highlight.
  - Right click menu does not clear the selection anymore, unless right clicking outside of the selection.


Version 2.2.5 (2019-11-17)
--------------------------

Features:

  - Single Click Mode has replaced Alternative Mouse Mode from:
    - edit -> preferences -> General -> Appearance/Behaviour -> Single click mode
  - Single Click Mode is off by default.
  - Single Click Mode cannot toggle a missing candidate in a single click because this would break the multi-selection behaviour. For this reason, toggling empty candidates must remain a double click.
  - Candidate Highlight Mode is toggled when LCtrl is held down. This makes it more clear that candidates are interactable when LCtrl is held down, and more obvious what you are editing.

Version 2.2.4 (2019-11-16)
--------------------------

Features:
  - Mouse click and drag selects cells
  - Ctrl + Drag appends to existing selection
  - Ctrl + Drag over existing selection toggled the selection

Version 2.2.3 (2019-11-14)
--------------------------

Features:
  - Double clicking a user set value removes it.
  - Options -> Show Candidate Highlight
  	- When enabled, mouse hover over candidates will highlight it as a preview.
	- Show Candidates must be enabled for this to work
	- Disabling Show Candidates disabled Show Candidate Highlight

Bugs fixed:
  - Show Candidates now saves correctly after the session is closed.
  - Shift + Click shifts the alternative color.

Version 2.2.2 (2019-11-10)
--------------------------

  - Added a "Solve Puzzle" option in the Puzzle menu.

Version 2.2.1 (2019-11-07) Fork by PseudoFish
---------------------------------------------

Features added:

  - Added an "Import Line" option in File.
  - Added an "Export Line" option in File.
  - File drag-and-drop to load a puzzle. Currently only supports files of this format:
  	- 067102000000000007100030000000360050000050892500008003046200000000000200051093004
	- .671.2...........71...3.......36..5.....5.8925....8..3.462...........2...51.93..4

Bugs fixed:

	no bug fixes

Version 2.2 (2012-07-31)
--------------------------

Features added:

  - #204: ColorKu mode added (thanks to ddyer and CCV)
  - #3486780: Complete ALS-Chain search in "find all steps"
  - #3476006: Improve keyboard handling
  - #3387327: Add mouse click detection
  - #3480600: Larger fonts for the visually challenged
  - #3463520: "Auto Tab" feature implemented
  - #3489431: Improve interoperability with SimpleSudoku
  - #3489725: Remove BrowserLauncher2 library
  - #3454460: Add a save command
  - #3387347: Switching to "Show all candidates" retains eliminations
  - #3510435: Clear cells using the mouse
  - #3515456: Locked candidates separation
  - #3520336: Reorganize preferences dialog
  - #3520334: Change UI for saving Sudokus as images
  - #3515747: Deviations, filters and alternate mouse mode
  - #3514371: Shade filter toggle buttons, when no candidates are available
  - #3514367: Candidate highlight for filters
  - #3523432: Make thickness of lines for boxes configurable
  - #3514351: Reduces puzzle difficulty for "hard" and "unfair"
  - #3527849: Save state of coloring cells/candidates

Bugs fixed:

  - #3476976: Debug message written to console window in batch
  - #3492680: Inconsistent fish sorting (Finned/Sashimi)
  - #3513608: Comparison exception in AlsComparer
  - #3513603: Search for all steps ignores ALS types
  - #3509643: Undo/Redo and all steps error
  - #3513680: Problems displaying AL-XZ
  - #3513851: Singles sorted wrongly in All steps panel
  - #3509639: Deleting values from cells is broken
  - #3509637: All steps can become out of sync
  - #3514467: addSudokuToHistory() might throw an exception
  - #3515379: Filter sets Invalid Candidate/Value
  - #3516396: Filters and Hidden Singles
  - #3514464: Changing maximum scores doesnt reset pregen puzzles
  - #3516415: Background generation request cancels generation
  - #3521140: Setting a cell via context menu doesnt enable Undo
  - #3530975: Setting values in regions is broken


Version 2.1.3 (2012-01-19)
--------------------------

Features added:

  no features added

Bugs fixed:

  - #3475960: HoDoKu doesnt start when no default config file exists


Version 2.1.2 (2012-01-16)
--------------------------

Features added:

  - #3452538: Backgound colors can alternate
  - #3436698: Command line option /sl added
  - #3473994: OR instead of AND for filter (configurable)
  - #3463740: Additional info in all steps view

Bugs fixed:

  - #3467383: Exception when starting from HoDoKu.jar
  - #3453781: Exception when batch creating puzzles
  - #3454452: Pressing <ctrl><c> in batch mode throws Exception
  - #3436963: Backdoor search is broken
  - #3425041: Cant always paste on MacOS
  - #3454627: volatile anzFound not updated atomically
  - #3432099: shift + numeric value not working


Version 2.1.1 (2011-10-23)
--------------------------

Features added:

  - #3427511: If a single cell is selected, the cursor display disappears after 1 second (configurable)

Bugs fixed:

  - #3427507: Brute Force solver broken in batch mode
  - #3427508: Template Set/Delete gives incorrect steps after a while
  - #3427509: A search for Kraken Fish may throw exceptions
  - #3427510: When candidates are colored, they are not displayed correctly


Version 2.1 (2011-10-20)
--------------------------

Features added:

  - #3387351: New extended print dialog / booklet printer
  - #3387344: Added a simplified mouse mode
  - #2940096: HoDoKu is now portable
  - #2940101/#3011686: Puzzles are now created in the background
  - #3387345: Progress marker added
  - #2990569: Added tooltips for main frame
  - #3324082: Highlight more than one candidate
  - #3296914: Remove candidates via keyboard when a range of cells is selected
  - #2970905/#3144406: Reworked loading of incorrect puzzles
  - #2995579: Speed up creation of training puzzles
  - #2689169: Allow URs/HRs with missing candidates
  - #3387335: Added fullscreen mode, made hint pane and toolbar hideable
  - #3387341: Changed display of focus (configurable)
  - #3387355: Added filter for bivalue cells
  - #3387356: Added game mode as startup option
  - #3387357: Added hint buttons in the toolbar
  - #3387359: Added a "solve up to" feature
  - #3394519: Coloring of cells with values is now configurable

Bugs fixed:

  - #3098767: Create Sudoku Window Hangs
  - #3394539: Store difficulty levels between runs
  - #3387212: In "Print Solution" example grids could be incorrect
  - #3303097: Fixed display of selected items under Nimbus
  - #3214361: Fixed errors when loading incorrect PM grids
  - #3134693: Hidden Pair incorrectly identified as Locked Pair
  - #3054092: Show deviations option is not remembered
  - #2954459: Arrows drawn incorrectly
  - #3387353: Set value in group of cells was broken


Version 2.0.1 (2010-04-14)
--------------------------

Features added:

  - #2974069: Import of givens into an existing grid
  - #2987128: Added print option for solution paths

Bugs fixed:

  - #2984538: XY-Chains not found


Version 2.0 (2010-03-11)
------------------------

Features added:

  - #2834110: New mouse interface
  - #2940268: Selection of region of cells using the keyboard
  - #2939789: It is now possible to color candidates as well as cells
  - #2939792: Links to user manual, solving guide and home page added to help menu
  - #2939800: Added seperate menu items for loading/saving configuration files
  - #2834095: <shift><ctrl><cursor> jumps to the next highlighted cell if filters are applied
  - #2940105: Savepoints implemented
  - #2940106: History of created puzzles added
  - #2940098: Learning/Training mode added
  - #2689174: Sort options added to "All Steps" panel
  - #2689183: Step progress measure added
  - #2689181: Backdoor searcher added
  - #2953525: Additional display options for fish added (/vf)

Bugs fixed:

  - #2790208: UR results not optimal
  - #2834090: When exporting invalid grids to the clipboard as PMs empty cells are treated incorrectly
  - #2834091: Result panels are not updated correctly when an invalid grid is loaded
  - #2834085: "Find all steps" doesnt find BUG+1
  - #2946982: position of horizontal divider bar is now adjusted correctly
  - #2834087: Shortest XY-Chain not found
  - #2790209: Accelerators for difficulty levels didnt work correctly
  - #2811672: Filters didnt work correctly when "Show candidates" was not checked
  - #2795464: Grouped Continuous Nice Loops could eliminate too many candidates


Version 1.2.4 (2009-05-08)
------------------------

Features added:

  - #2772853: Use Ctrl-Cursor to go to next unsolved cell

Bugs fixed:

  - #2762372: In coloring not all possible eliminations were found
  - #2765903: Incorrect Siamese handling in Kraken search
  - #2765909: When searching for Kraken Fish ALS were always used
  - #2788800: "Only one fish per elimination" didn't return the smallest fish
  - #2788799: Search for all fishes finds Basic Fish only when searching for Mutant
  - #2788798: Search for Franken/Mutant returns Basic Fish


Version 1.2 (2009-04-04)
------------------------

Features added:

  - #2723180: Death Blossom added
  - #2689196: "Enter game" has been renamed to "New Givens", "Modify givens" added
  - #2689167: "Reset games" added, "Restart Game" reworked
  - #2690534: Added Type 1/Type 2 to Kraken Fish output
  - #2533240: Added Dual 2-String Kites and Dual Empty Rectangles
  - #2691712: Added a "One step only" option for fish search
  - #2689171: Added support for Siamese Fish
  - #2690539: Allow selection of candidates in (Kraken) Fish search
  - #2512887: ALS Chaining has been redone to allow overlapping ALS (optional)
  - #2691742: Kraken Fish config added (for general solver)
  - #2723253: ALs overlapping is now configurable
  - #2723247: ALS nodes are now optional in NiceLoops/AICs and Forcing Chains/Nets
  - #2689864: Statistics options added for batch solve (/vst)
  - #2689186: Steps can be reordered using drag and drop

Bugs fixed:

  - #2690494: All steps panel not properly reset when new puzzle is created
  - #2689165: Text for Template Set didn't show the affected cells
  - #2689164: XY-Chain notation didn't show start and end links
  - #2690582: All steps panel: Quick links were not saved at program exit


Version 1.1 (2009-03-03)
------------------------

Features added:

  - #2512895: Support for Kraken Fish added
  - Support for Turbot fish added
  - #2579893: ERs with only two cells in the ER are now found
  - #2512881: Sue de Coqs with the same additional candidate in both
              flanking sets are now supported
  - #2512876: Support for doubly linked ALS-XZ added
  - #2512874: Change chain ordering (size of ALS is taken into account)
  - #2577506: Fish type (endo fins/cannibalism) added in console output
  - New command line options: /f, /bsa, /bsaf, /stdin
  - #2634172: ALS nodes in Continuous Nice Loops are now handled
  - "Find all steps" is now freely configurable

Bugs fixed:

  - #2619238: ALS might be not displayed correctly in steps containing more than one chain
  - #2619244: ALS buddies handled incorrectly in chains
  - #2628375: Some AICs were not found
  - #2634176: AICs could overlap

Version 1.0 (2009-01-29)
------------------------

Features added:

  - Enhanced command line support: searching for puzzles with certain properties is
    now freely configurable (options /c /s /lt /so added)
  - Added a few short cuts
  - #2530890: More output options in batch processing (options /vp /vs /vg added)
  - #2543821: Custom console for hodoku-x.x.x.exe added
  - #2541410: '0' instead of '.' in export possible
  - #2536950: Window layout can be saved at shutdown

Bugs fixed:

  - #2539619: Selecting tabs in right pane didn't adjust menus
  - #2537408: Clicking a step after pressing F12 threw an exception
  - #2536944: Main window was too large
  - #2533245: Numpad didn't work
  - #2531752: Reading the config file didn't correctly adjust solver steps
  - #2530555: Forcing Chain notation was broken
  - #2512869: Nice Loop Notation was incomplete for Continuous Nice Loops and AICs
  - #2512851: Background colors were not corrected when deleting solutions
  - #3510433: SummaryPanel errors



Version 0.9.0 (2009-01-15)
--------------------------

This is the first release of HoDoKu. No new features will be added before the release
of version 1.0. This release is extensively tested and should be stable.
