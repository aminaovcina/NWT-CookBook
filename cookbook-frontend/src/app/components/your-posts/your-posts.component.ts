import { Component, OnInit } from '@angular/core';
import { Recipe } from '../../models/recipe';
import { RecipeService } from '../../service/recipe.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-your-posts',
  templateUrl: './your-posts.component.html',
  styleUrls: ['./your-posts.component.scss']
})
export class YourPostsComponent implements OnInit {
  private user: User = JSON.parse(sessionStorage.getItem('account')).user;
  recipeList : Recipe[] = [];
  recipe : Recipe;
  odgovor: any;
  account: any = null;
  obrisano: boolean;
  privilegovan: number;
  user_id: number;
  constructor(private recipeService: RecipeService) { 
    this.account = JSON.parse(sessionStorage.getItem('account'));
  }
  ngOnInit() {
    this.user_id = JSON.parse(sessionStorage.getItem('account')).user.id;
    this.privilegovan = this.user.role.roleId;
    this.recipeService.getYourRecepti(this.user_id)
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
