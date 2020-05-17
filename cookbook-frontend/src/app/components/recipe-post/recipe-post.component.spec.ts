import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipePostComponent } from './recipe-post.component';

describe('RecipePostComponent', () => {
  let component: RecipePostComponent;
  let fixture: ComponentFixture<RecipePostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecipePostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipePostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
