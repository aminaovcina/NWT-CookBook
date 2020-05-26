import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../../service/recipe.service';
import { User } from 'src/app/models/user';
import { Recipe } from '../../models/recipe';
@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.scss']
})
export class RecipeListComponent implements OnInit {
  // private user: User = JSON.parse(sessionStorage.getItem('account')).user;
  recipeList : Recipe[] = [];
  odgovor: any;
  constructor(private recipeService: RecipeService) { 
    this.odgovor = this.recipeService.getRecipes();
  }

  ngOnInit() {
  }


}
