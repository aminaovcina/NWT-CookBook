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

  account: any;
  objavljeno: boolean;
  dishList : Dish[] = [];
  categoryList: Category[] = [];
  recipeCategory: number[] = [];
  model : Recipe = new Recipe();
  constructor(private recipeService: RecipeService, private datePipe: DatePipe) { 
    this.account = JSON.parse(sessionStorage.getItem('account'));
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
      // "accountId": JSON.parse(sessionStorage.getItem('account')).id,
      "accountId": 1,
      "dishId": dish,
      "postDate": datum,
      "cookingTime": cookingtime
    }
    this.recipeService.postRecipe(recipePost).subscribe(response => {
      this.objavljeno = true;
      console.log(response.body)
      this.recipeCategory.forEach(element => {
        var recipeCategoryPost = {
          "category": this.categoryList[element],
          "recipe": response.body
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
