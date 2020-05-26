import { Dish } from "./dish";

export class Recipe{
    id: number;
    title: string;
    description: string;
    dish: Dish;
    dishId: number;
    accountId: number;
    cookingTime: number;
    postDate: Date;
    cookingTemperature: number;

}