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
    postdate: string;
    cookingTime: number;
    constructor(){
        this.id = null;
        this.title = '';
        this.description = '';
        this.dish = null;
        this.cookingTemperature = null;
        this.cookingTime = null;
        this.accountId = null;
        this.dishId = null;
        this.postDate = null;
        this.postdate = '';
        this.cookingTime = null;
    }

}
