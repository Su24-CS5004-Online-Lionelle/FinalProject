# GUI Application Manual

## Table of Contents

- [GUI Application Manual](#gui-application-manual)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Getting Started](#getting-started)
    - [Launching the Application](#launching-the-application)
    - [User Interface Overview](#user-interface-overview)
  - [Features](#features)
    - [Generate New List (API Collection)](#generate-new-list-api-collection)
      - [Description](#description)
      - [How to Use](#how-to-use)
    - [Filter Trivia Questions (API Collection)](#filter-trivia-questions-api-collection)
      - [Description](#description-1)
      - [How to Use](#how-to-use-1)
    - [Move Trivia Questions Between Collections](#move-trivia-questions-between-collections)
      - [Description](#description-2)
      - [How to Use](#how-to-use-2)
    - [Sort Trivia Questions (User Collection)](#sort-trivia-questions-user-collection)
      - [Description](#description-3)
      - [How to Use](#how-to-use-3)
    - [Save Trivia Questions (User Collection)](#save-trivia-questions-user-collection)
      - [Description](#description-4)
      - [How to Use](#how-to-use-4)
    - [Load Trivia Questions (User Collection)](#load-trivia-questions-user-collection)
      - [Description](#description-5)
      - [How to Use](#how-to-use-5)

## Introduction

Welcome to the Trivia Manager 1.0 Manual. This document serves as a guide for our GUI application, with aim to provide comprehensive instructions on how to use it.

## Getting Started

### Launching the Application

- ?

### User Interface Overview

This GUI offers the ability to manage two collections, comprised of trivia questions. The API collection (1) is driven by making calls to the [Open Trivia Database API](https://opentdb.com/api_config.php); when prompted, users can select up to four trivia categories at any time (via, 2), in which randomized questions pertaining to each selected category are returned within the corresponding window. Users have the ability to apply / un-apply filters using certain attributes of the trivia questions, within the API collection (via, 3).

![GUI Layout](../Manual/images/annotated_layout.png)

The user's collection (6) is driven by which trivia questions users decide to curate; they can add questions from previously saved out collections (via, 5) and/or can move questions from the API collection into itself or from itself into the API collection (both, via 4). As suggested, users can save out their collection of trivia questions at any time (via, 5). Additionally, users have the ability to sort their curated collection on specific trivia question attributes (via, 7), in either ascending or descending order.

## Features

### Generate New List (API Collection)

#### Description

- Offers users the ability to refresh the existing API collection. 
- Users can only specify up to four trivia categories per call; each category will return a static set of twenty five questions, regardless of how many categories are selected. 
- Executing a call to the API will overwrite the existing API trivia collection - therefore, be sure to move any trivia questions of interest over to your collection before proceeding. 
- The user session is tracked behind the scenes, of which should prevent any duplicate questions over the course of multiple calls on the same category within the same session.
- To the extent all trivia questions within any requested categories have been exhausted within the session and therefore cannot meet the twenty five question minimum, an error message will present itself, notifying the user of this issue; any other categories bundled within the same request will not be added to the collection. NOTE: Questions from these bundled categories may be exhausted when this occurs, without notice.
- Be advised that some categories that will exhaust quickly (i.e., ANIMALS, ART, CELEBRITIES).
  
#### How to Use

- Press the `Generate New List` button, located on the bottom left hand side of the GUI.

<div style="display: flex; justify-content: space-between;">
    <img src="../Manual/images/blank_categories.png" style="width: 35%; margin-right: 5px;">
    <img src="../Manual/images/single_category.png" style="width: 35%; margin-right: 5px;">
    <img src="../Manual/images/multi_category.png" style="width: 35%;">
</div>

- Continue by selecting one or many (up to four) categories in the pop-up window; use control / shift while selecting to maintain multi-selection. Scroll up/down to view all categories.
- Once your selection is made, select `OK`, otherwise `Cancel` if desired.
- At this point, you should see your trivia questions appearing in the left-most window (see below). NOTE: this usually takes a few seconds due to limitations on consecutive API calls.

![Test API Generation](../Manual/images/test_api_gen.png)

- You are free to regenerate a new list of trivia questions if desired; just re-cycle through the same process for either the categories you selected or for newly selected categories.

### Filter Trivia Questions (API Collection)

#### Description

- Offers users ability to temporarily reduce the existing API collection, by applying filters on the trivia question attributes (i.e., category, difficulty, question type).
- Supports multi-select filtering.
- Resetting the filters requires manual de-selection of actively applied filters.
- Filter options are always dynamically rendered based on the questions that actively exist within the API collection.

#### How to Use

- Select the yellow funnel icon above the API collection; this will present the user with checkboxes to select the filters they'd like to apply.
- Once checked, proceed with selecting the apply filters button; the collection will filter accordingly
- To reset filters, repeat steps but instead, un-check actively checked boxes and re-apply.

<div style="display: flex; justify-content: space-between;">
    <img src="../Manual/images/filter_checkboxes.png" style="width: 35%; margin-right: 5px;">
    <img src="../Manual/images/filter_checked.png" style="width: 35%; margin-right: 5px;">
    <img src="../Manual/images/filter_results.png" style="width: 35%;">
</div>

### Move Trivia Questions Between Collections

#### Description

- Offers users ability to move questions between the API collection and their own collection.
- Supports one question, per exchange.
- Duplicates are handled by condensing into one question, within the target collection; this may originate from loading trivia questions from previously saved collections, of which are not tracked as part of the user's session / API exhaust.
- If a sort is actively applied to the User's collection, the exchanged trivia question will appear sorted accordingly
- If  is actively applied to the API's collection, the exchanged trivia question will be filtered out of view, thereby requiring the filter be un-applied in order to view said question.

#### How to Use

- Select a question within either collection (for this example, the API collection); once selected, the arrow corresponding to the appropriate exchange direction should activate.
- Select the activated arrow to initiate the exchange; once selected, the selected question should now appear in the opposing collection.

<div style="display: flex; justify-content: space-between;">
    <img src="../Manual/images/arrow_idle.png" style="width: 50%; margin-right: 5px;">
    <img src="../Manual/images/arrow_select.png" style="width: 50%; margin-right: 5px;">
</div>

- As depicted, this can be reversed, by re-selecting the question and sending it back in the opposite direction.

<div style="display: flex; justify-content: space-between;">
    <img src="../Manual/images/arrow_result.png" style="width: 50%; margin-right: 5px;">
    <img src="../Manual/images/arrow_reverse.png" style="width: 50%; margin-right: 5px;">
</div>

### Sort Trivia Questions (User Collection)

#### Description

- Offers users ability to sort their own collection based on ascending / descending order of trivia question attributes (i.e., category, difficulty, question type).
- Default sort utilizes ?
- Resetting the sorting requires ?

#### How to Use

- Dropdown the ordering options and select the desired attribute to sort by; the arrow direction depicts either ascending (down) or descending (up) order.
- Once selected (for example, difficulty descending), the questions should be sorted from hard (top) to easy (bottom).

<div style="display: flex; justify-content: space-between;">
    <img src="../Manual/images/sort_idle.png" style="width: 50%; margin-right: 5px;">
    <img src="../Manual/images/sort_options.png" style="width: 50%; margin-right: 5px;">
</div>

- To swap the order, select the arrow to change sort direction.

<div style="display: flex; justify-content: space-between;">
    <img src="../Manual/images/sort_apply.png" style="width: 50%; margin-right: 5px;">
    <img src="../Manual/images/sort_redirect.png" style="width: 50%; margin-right: 5px;">
</div>

### Save Trivia Questions (User Collection)

#### Description

- Offers users the ability to automatically save out their existing collection of trivia questions as `.json`, in addition to separate `.pretty` files for both their questions and corresponding answers (i.e., three files).
- Requires folder designation.

#### How to Use

- Once questions have been placed in the user's collections, selected the `Save` button; this will prompt the user to designate a new folder for the outputted files to land in.

<div style="display: flex; justify-content: space-between;">
    <img src="../Manual/images/save_select.png" style="width: 50%; margin-right: 5px;">
    <img src="../Manual/images/save_success.png" style="width: 50%; margin-right: 5px;">
</div>

- Once successfully saved, users should be able to navigate to their designated folder, where they can find all three outputted files from the program.

![Save Folder Contents](../Manual/images/save_folder_contents.png)

<div style="display: flex; justify-content: space-between;">
    <img src="../Manual/images/save_questions.png" style="width: 35%; margin-right: 5px;">
    <img src="../Manual/images/save_answers.png" style="width: 35%; margin-right: 5px;">
    <img src="../Manual/images/save_json.png" style="width: 35%;">
</div>

### Load Trivia Questions (User Collection)

#### Description

- Offers users the ability to load in previously saved out collections of trivia questions, via `.json` file that creates during save.
- Requires file designation.
- NOTE: Loading process will de-duplicate pre-existing and incoming questions. Pre-existing sort may not apply automatically.
  
#### How to Use

- Select `Load`; designate `.json` file and select `Open`.

<div style="display: flex; justify-content: space-between;">
    <img src="../Manual/images/open_select.png" style="width: 50%; margin-right: 5px;">
    <img src="../Manual/images/open_result.png" style="width: 50%; margin-right: 5px;">
</div>