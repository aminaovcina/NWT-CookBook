import { Component, OnInit } from '@angular/core';
import { Recipe } from '../../models/recipe';
import { RecipeService } from '../../service/recipe.service';

@Component({
  selector: 'app-your-posts',
  templateUrl: './your-posts.component.html',
  styleUrls: ['./your-posts.component.scss']
})
export class YourPostsComponent implements OnInit {
 // private user: User = JSON.parse(sessionStorage.getItem('account')).user;
  recipeList : Recipe[] = [];
  recipe : Recipe;
  odgovor: any;
  account: any = true;
  obrisano: boolean;
  constructor(private recipeService: RecipeService) { 
  // this.account = JSON.parse(sessionStorage.getItem('account'));
  }
  ngOnInit() {
    this.recipeService.getYourRecepti()
    .subscribe(heroes => this.recipeList = heroes);
  }
  deleteRecipe(idRecepta){
    this.recipeService.deleteRecipe(idRecepta).subscribe(response => {
      this.obrisano = true;
      this.ngOnInit();
    },
    err =>{  
      this.obrisano = false; 
    });
    if(this.obrisano != true){
      this.obrisano = false;
    }
   
  
  }


}
