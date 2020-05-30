import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../../service/recipe.service';
import { User } from 'src/app/models/user';
import { Recipe } from '../../models/recipe';
import { Observable } from 'rxjs';
import { map } from "rxjs/operators";
@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.scss']
})
export class RecipeListComponent implements OnInit {
  // private user: User = JSON.parse(sessionStorage.getItem('account')).user;
  recipeList : Recipe[] = [];
  recipe : Recipe;
  odgovor: any;
  account: any;
  constructor(private recipeService: RecipeService) { 
    console.log("dodje")
   
  console.log(this.recipeList)
  this.account = JSON.parse(sessionStorage.getItem('account'));
  
  }

  ngOnInit() {
    this.recipeService.getRecepti()
    .subscribe(heroes => this.recipeList = heroes);
    console.log(this.recipeList)
  }


}
