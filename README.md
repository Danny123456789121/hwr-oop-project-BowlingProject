# Bowling Kata

This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at HWR Berlin (summer term 2022).

> :warning: This code is for educational purpose only. Do not rely on it!


## Abstract

This project implements the classic bowling kata in Java with 100% test coverage.

The features of this project are a full implementation of the classic 10-pin bowling scoring system, an alternate 9-pin scoring system based on "Berg- und Talfahrt" variant and a console output for the bowling game.

The biggest problems in the implementation were a well arranged console output and the implementation of the 10-pin bowling rules, because a thrown strike or spare has to wait for the results of the next throws, as they are included in the final calculation of the throw.

> Berg- und Talfahrt Rules: 
> <br/>
> + There are no bonus points like strike or spare. 
> + If 9 Pins are knocked down the Frame is over
> 
> Even Frames:
> * goal is to get the lowest possible score Frame (-1)
> * the achieved score in a frame will be subtracted from the total score
> * if no pins are knocked down, 9 points are deducted from the total score
> 
> Odd Frames:
> * goal is to get the highest possible score in a Frame (9)
> * the achieved score in a frame will be added to the total score
> * if no pins are knocked down, 0 points are added to the total score

## Feature List


| Number |          Feature            | Tests          |
|--------|-----------------------------|----------------|
|   1    |   10-Pin Bowling Ruleset    |       yes      |
|   2    |    9-Pin Bowling Variant    |       yes      |
|   3    | Bowling game console output |  unnecessary   |

