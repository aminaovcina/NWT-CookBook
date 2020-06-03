import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  SHOWRECIPELIST: boolean = false;
  SHOWPOST: boolean = false;
  SHOWLOGIN: boolean = false;
  SHOWREGISTRATION: boolean = false;
  SHOWFAVORITE: boolean = false;
  SHOWSUBSCRIBE: boolean = false;
  SHOWEDITPROFILE: boolean = false;
  SHOWYOURPOSTS: boolean = false;
  constructor() { }

  ngOnInit() {
  }
  registration(){
    this.SHOWREGISTRATION = true;
    this.SHOWSUBSCRIBE = false;
    this.SHOWFAVORITE = false;
    this.SHOWLOGIN = false;
    this.SHOWRECIPELIST = false;
    this.SHOWPOST = false;
    this.SHOWEDITPROFILE = false;
    this.SHOWYOURPOSTS = false;
  }
  login(){
    this.SHOWREGISTRATION = false;
    this.SHOWSUBSCRIBE = false;
    this.SHOWFAVORITE = false;
    this.SHOWLOGIN = true;
    this.SHOWRECIPELIST = false;
    this.SHOWPOST = false;
    this.SHOWEDITPROFILE = false;
    this.SHOWYOURPOSTS = false;
  }
  recipelist(){
    this.SHOWREGISTRATION = false;
    this.SHOWSUBSCRIBE = false;
    this.SHOWFAVORITE = false;
    this.SHOWLOGIN = false;
    this.SHOWRECIPELIST = true;
    this.SHOWPOST = false;
    this.SHOWEDITPROFILE = false;
    this.SHOWYOURPOSTS = false;
  }
  post(){
    this.SHOWREGISTRATION = false;
    this.SHOWSUBSCRIBE = false;
    this.SHOWFAVORITE = false;
    this.SHOWLOGIN = false;
    this.SHOWRECIPELIST = false;
    this.SHOWPOST = true;
    this.SHOWEDITPROFILE = false;
    this.SHOWYOURPOSTS = false;
  }
  favorite(){
    this.SHOWREGISTRATION = false;
    this.SHOWSUBSCRIBE = false;
    this.SHOWFAVORITE = true;
    this.SHOWLOGIN = false;
    this.SHOWRECIPELIST = false;
    this.SHOWPOST = false;
    this.SHOWEDITPROFILE = false;
    this.SHOWYOURPOSTS = false;
  }
  subscribe(){
    this.SHOWREGISTRATION = false;
    this.SHOWSUBSCRIBE = true;
    this.SHOWFAVORITE = false;
    this.SHOWLOGIN = false;
    this.SHOWRECIPELIST = false;
    this.SHOWPOST = false;
    this.SHOWEDITPROFILE = false;
    this.SHOWYOURPOSTS = false;
  }
  editprofile(){
    this.SHOWREGISTRATION = false;
    this.SHOWSUBSCRIBE = false;
    this.SHOWFAVORITE = false;
    this.SHOWLOGIN = false;
    this.SHOWRECIPELIST = false;
    this.SHOWPOST = false;
    this.SHOWEDITPROFILE = true;
    this.SHOWYOURPOSTS = false;
  }

  yourPosts(){
    this.SHOWREGISTRATION = false;
    this.SHOWSUBSCRIBE = false;
    this.SHOWFAVORITE = false;
    this.SHOWLOGIN = false;
    this.SHOWRECIPELIST = false;
    this.SHOWPOST = false;
    this.SHOWEDITPROFILE = false;
    this.SHOWYOURPOSTS = true;
  }

}
