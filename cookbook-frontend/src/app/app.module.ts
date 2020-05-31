import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecipeListComponent } from './components/recipe-list/recipe-list.component';
import { RecipePostComponent } from './components/recipe-post/recipe-post.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { SubscriptionComponent } from './components/subscription/subscription.component';
import { FavoritesComponent } from './components/favorites/favorites.component';
import { UserService } from './service/user.service';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { headerInterceptor } from './service/headerinterceptor';
import { ProfileEditComponent } from './components/profile-edit/profile-edit.component';
import { RecipeService } from './service/recipe.service';

@NgModule({
  declarations: [
    AppComponent,
    RecipeListComponent,
    RecipePostComponent,
    DashboardComponent,
    LoginComponent,
    RegistrationComponent,
    SubscriptionComponent,
    FavoritesComponent,
    ProfileEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularFontAwesomeModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UserService, RecipeService, DatePipe, HttpClient, {
    provide: HTTP_INTERCEPTORS,
    useClass: headerInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
