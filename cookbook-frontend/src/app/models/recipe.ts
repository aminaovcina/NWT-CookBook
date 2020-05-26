import { Dish } from "./dish";

export class Recipe{
    id: number;
    title: string;
    description: string;
    dish: Dish;
    cookingTemperature: number;
    accountId: number;
    dishId: number;
    postDate: Date;
    cookingTime: number;

}
