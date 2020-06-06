import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../../service/recipe.service';
import { User } from 'src/app/models/user';
import { Recipe } from '../../models/recipe';
import { Observable } from 'rxjs';
import { map } from "rxjs/operators";
import { Dish } from '../../models/dish';
import { Category } from '../../models/category';
@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.scss']
})
export class RecipeListComponent implements OnInit {
  // private user: User = JSON.parse(sessionStorage.getItem('account')).user;
  recipeList : Recipe[] = [];
  dishList: Dish[] = [];
  categoryList: Category[] = [];
  recipe : Recipe;
  odgovor: any;
  account: any = null;
  constructor(private recipeService: RecipeService) { 
  this.account = JSON.parse(sessionStorage.getItem('account'));
  }
  ngOnInit() {
    this.recipeService.getRecepti()
    .subscribe(heroes => this.recipeList = heroes);
    
    this.recipeService.getDishs()
    .subscribe(heroes => this.dishList = heroes);

    this.recipeService.getCategories()
    .subscribe(heroes => this.categoryList = heroes);
  }

  dishRecipes(dishId){
    console.log("udje")
    this.recipeService.getReceptiByDish(dishId)
    .subscribe(heroes => this.recipeList = heroes);
  }

  categoryRecipes(categoryId){
    this.recipeService.getReceptiByCategory(categoryId)
    .subscribe(heroes => this.recipeList = heroes);
  }


}
