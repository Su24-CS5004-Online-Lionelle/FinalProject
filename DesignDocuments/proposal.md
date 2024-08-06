### What are you building?

Our team is proposing to build an application that allows a user to manage a collection of trivia questions. Upon entry, the user will be displayed two panels. The panel on the left will display the programs main collection of trivia questions (offers filter, filter-reset functionality); the panel on the right will display the current subset of trivia questions that the users selects from the main collection; this subset will be sortable / exportable (i.e., pretty). Questions can be moved between panels by putting (singular / multiple) questions into focus, selecting the appropriate arrow, of which will trigger the directional exchange of the selected questions. The main collection can be generated / re-generated (randomized) using API calls.

### What are the initial features for the application?

- Making API requests, to populate randomized trivia questions within the program's main collection pane. This should support multiple requests, in the event that the user wants to view an entirely new set of questions to choose from. NOTE: session token needs to be reset if all questions are retrieved in a single question. Default will be 100 questions per request.

- Handling the API requests, including processing the trivia questions, storing metadata, etc. (this is important for supporting sorting / filtering logic).

- Building the GUI; supports main/user collection views, buttons, etc.

- Exporting user collection to pretty-print formatted file.

## What are the minimum additional features you plan to implement?

- Sorting user collection.

- Filtering main collection (category, difficulty level, question type)

- Loading previously outputted user collections.

## What are your stretch goals (features beyond the minimum)?

- Interactive trivia player.

## Work Breakdown (Features)

- View (Will, Adam) / Controller (Rachael, Yaguang)
  - Displaying / Wiring API Button to trigger model functionality
  - Displaying TriviaQuestions within JList for both API / User Collection; needs to support single selection and subsequent index identification
  - (TBD) Filtering display / wiring for API collection
    - Should this be one icon with a drop down and apply button; should this be real time, as each individual filter is selected.
    - Displaying / wiring filter removal.
  - Displaying / wiring left and right arrows to trigger exchange of single TriviaQuestion between API and User collection, updating questions in real time
  - (TBD) Sorting display / wiring for user collection
  - Displaying / wiring Load Button; should present some form of file browser
  - Displaying / wiring Save Button; should present some form of file browser
- Model (All)
  - API Button (Adam)
    - When someone selects the API button, the API needs to be called with a random set of 100 trivia questions; these should then be processed as TriviaQuestion records, and stored (overwritten, if appl.) within the API collection.
  - Filtering (Rachael)
    - Support filtering on abstract trivia question collections; this should maintain true collection and filtered collection.
    - Support removal of applied filters / restoring true collection.
  - Arrows (Will)
    - Performing the transaction between any two collections, given a direction and particular Trivia Question
  - Sorting (Rachael)
    - Using metadata elements to have trivia collections sort themselves, of which the sorted order should be reflected in the GUI
  - Save (Yaguang)
    - Writing out the trivia questions to pretty print (.txt file)
  - Load (Yaguang)
    - Reading in pretty print (.txt file) and representing as the programs current user collection

## Timeline (High Level Milestones)

Friday, July 26th: Project proposal
Saturday July 27th - Sunday, July 28th: Finalized architecture, model (time permitting, view / controller) interface(s), functional design, GUI layout
Friday, August 2nd: Have model completed / tested.
Saturday, August 3rd: Intensive review of model. Begin view / controller.
Wednesday, August 7th: Have controller / view completed / tested.
Wednesday, August 7th - August 8th: Finishing touches / debugging / demonstration rehearsal.
Friday, August 9th: Product demo.
Monday, August 12th: Final product + retrospective due.

## Discussion Track

1. Adam - Elevator Pitch
2. Round robin to introduce specific model features (in above order) - at this point, showcase the GUI rendering
3. Rachael - UML showcase (open discussion)
4. Stop and listen for feedback.
5. Open Questions
   1. Model structures; should it be one file?
   2. Will this API use cover the project requirement?
   3. Double check on whether we are covering our "save out" requirement?
   4. Clarification on the use of packages?
   5. Do we need to account for gradle?