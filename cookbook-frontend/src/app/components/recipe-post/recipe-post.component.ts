import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { RecipeService } from '../../service/recipe.service';
import { Dish } from '../../models/dish';
import { Recipe } from '../../models/recipe';
import { Category } from '../../models/category';

@Component({
  selector: 'app-recipe-post',
  templateUrl: './recipe-post.component.html',
  styleUrls: ['./recipe-post.component.scss']
})
export class RecipePostComponent implements OnInit {
  account: any = null;
  objavljeno: boolean;
  dishList : Dish[] = [];
  categoryList: Category[] = [];
  recipeCategory: number[] = [];
  model : Recipe = new Recipe();
  constructor(private recipeService: RecipeService, private datePipe: DatePipe) {
    this.account = JSON.parse(sessionStorage.getItem('account'));
    console.log(this.account);
  }
  onCheckChange(categoryId){
    this.recipeCategory.push(categoryId);
    console.log(this.recipeCategory);
  }
  postRecipe(){
    let description = (<HTMLInputElement>document.getElementById('opis')).value;
    let temperatura = (<HTMLInputElement>document.getElementById('temperatura')).value;
    let title = (<HTMLInputElement>document.getElementById('naziv')).value;
    let cookingtime = (<HTMLInputElement>document.getElementById('vrijeme')).value;
    let dish = (<HTMLSelectElement>document.getElementById('dish')).value;
    let dishobject = this.dishList.find(x => x.id.toString() == dish);
    var today = Date.now();
    var datum = this.datePipe.transform(Date.now(), 'yyyy-MM-dd');
    var recipePost = {
      "title": title,
      "description": description,
      "dish" : dishobject,
      "cookingTemperature": temperatura,
      "account_id": 1,
      "dish_id": dish,
      "postDate": today,
      "postdate": datum,
      "cookingTime": cookingtime
    }
    this.recipeService.postRecipe(recipePost).subscribe(response => {
      this.objavljeno = true;
      console.log(response.body)
      this.recipeCategory.forEach(element => {
        var recipeCategoryPost = {
          "category": this.categoryList.find(x => x.id == element),
          "recipe": {
            "id": response.body,
            "title": title,
            "description": description,
            "dish" : dishobject,
            "cookingTemperature": temperatura,
            "account_id": 1,
            "dish_id": dish,
            "postDate": today,
            "postdate": datum,
            "cookingTime": cookingtime
          },
          "category_id": element,
          "recipe_id": response.body
        }
        this.recipeService.postRecipeCategory(recipeCategoryPost).subscribe(novi => {

        });

      });
    },
    err =>{
      this.objavljeno = false;
    });
    if(this.objavljeno != true){
      this.objavljeno = false;
    }

  }
  ngOnInit() {
    this.recipeService.getDishs()
    .subscribe(heroes => this.dishList = heroes);

    this.recipeService.getCategories()
    .subscribe(heroes => this.categoryList = heroes);
  }

}
