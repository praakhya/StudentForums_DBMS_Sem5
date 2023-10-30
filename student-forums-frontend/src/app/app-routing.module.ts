import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ForumsComponent } from './forums/forums.component';
import { ForumComponent } from './forums/forum/forum.component';
import { CreatePostComponent } from './create-post/create-post.component';
const routes: Routes = [
  { path: 'profile', component: ProfileComponent },
  {path: '', component: HomeComponent},
  {path: 'home', component: DashboardComponent},
  {path: 'forums', component: ForumsComponent},
  {path: 'forums/:id', component:ForumComponent},
  {path: 'create-post/:forumId/:userId',component:CreatePostComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
