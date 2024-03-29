import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { HelloworldComponent } from '../helloworld/helloworld.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field'
import { SignupComponent } from './signup/signup.component';
import { ProfileComponent } from './profile/profile.component';
import {MatIconModule} from '@angular/material/icon';
import { StudentProfileComponent } from './profile/student-profile/student-profile.component';
import { FacultyProfileComponent } from './profile/faculty-profile/faculty-profile.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatSelectModule} from '@angular/material/select';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatDividerModule} from '@angular/material/divider';
import {MatListModule} from '@angular/material/list';
import {MatChipsModule} from '@angular/material/chips';
import {MatMenuModule} from '@angular/material/menu';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTreeModule} from '@angular/material/tree';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatCheckboxModule} from '@angular/material/checkbox';

import { EditorModule } from '@tinymce/tinymce-angular';
import { DialogModule } from '@angular/cdk/dialog';
import { CreatePostComponent } from './create-post/create-post.component';


import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ForumsComponent } from './forums/forums.component';
import { ForumComponent } from './forums/forum/forum.component';
import { PostComponent } from './post/post.component';
import { PostsComponent } from './posts/posts.component';
import { PostReplyComponent } from './post-reply/post-reply.component';
import { ReplyToPostComponent } from './reply-to-post/reply-to-post.component';
import { ResourcesComponent } from './resources/resources.component';
import { DragDirective } from './dragDrop.directive';
import { ResourceComponent } from './resource/resource.component';
import { SectionComponent } from './section/section.component';

@NgModule({
  declarations: [
    AppComponent,
    HelloworldComponent,
    LoginComponent,
    SignupComponent,
    ProfileComponent,
    StudentProfileComponent,
    FacultyProfileComponent,
    HomeComponent,
    DashboardComponent,
    ForumsComponent,
    ForumComponent,
    PostComponent,
    CreatePostComponent,
    PostsComponent,
    PostReplyComponent,
    ReplyToPostComponent,
    ResourcesComponent,
    DragDirective,
    ResourceComponent,
    SectionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,

    MatCardModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatSidenavModule,
    MatSelectModule,
    MatToolbarModule,
    MatButtonToggleModule,
    MatDividerModule,
    MatListModule,
    MatChipsModule,
    MatMenuModule,
    MatExpansionModule,
    MatSnackBarModule,
    EditorModule,
    DialogModule,
    MatTreeModule,
    MatTableModule,
    MatPaginatorModule,
    MatCheckboxModule
    
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
