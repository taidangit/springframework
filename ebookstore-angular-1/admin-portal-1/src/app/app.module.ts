import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HttpClientModule }  from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { EditorModule } from '@tinymce/tinymce-angular';

import { 
			MatButtonModule, 
			MatToolbarModule, 
			MatGridListModule,
			MatInputModule,
      MatSelectModule,
      MatSlideToggleModule,
      MatCardModule,
      MatListModule,
      MatDialogModule,
      MatTableModule,
      MatPaginatorModule

		} from '@angular/material';

import 'hammerjs';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// Service
import { LoginService } from './services/login.service';
import { AddBookService } from './services/add-book.service';
import { UploadImageService } from './services/upload-image.service';
import { GetBookListService } from './services/get-book-list.service';
import { GetBookService } from './services/get-book.service';
import { EditBookService } from './services/edit-book.service';
import { RemoveBookService } from './services/remove-book.service';

// Routing
import { LoginRoutingModule }  from './components/login/login-routing.module';
import { AddBookRoutingModule }  from './components/add-book/add-book-routing.module';
import { BookListRoutingModule }  from './components/book-list/book-list-routing.module';
import { ViewBookRoutingModule }  from './components/view-book/view-book-routing.module';
import { EditBookRoutingModule }  from './components/edit-book/edit-book-routing.module';


// Component
import { AddBookComponent } from './components/add-book/add-book.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { LoginComponent } from './components/login/login.component';
import { BookListComponent, DialogOverviewExampleDialog } 
            from './components/book-list/book-list.component';
            
import { ViewBookComponent } from './components/view-book/view-book.component';
import { EditBookComponent } from './components/edit-book/edit-book.component';


const appRoutes: Routes = [
	
];

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent,
    AddBookComponent,
    BookListComponent,
    ViewBookComponent,
    EditBookComponent,
    DialogOverviewExampleDialog
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    EditorModule,
   
    LoginRoutingModule,
    AddBookRoutingModule,
    BookListRoutingModule,
    ViewBookRoutingModule,
    EditBookRoutingModule,
    
   	RouterModule.forRoot(appRoutes),

    MatButtonModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatGridListModule,
    MatInputModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatCardModule,
    MatListModule,
    MatDialogModule,
    MatTableModule,
    MatPaginatorModule 
  ],

  providers: [
  	LoginService,
    AddBookService,
    UploadImageService,
    GetBookListService,
    GetBookService,
    EditBookService,
    RemoveBookService
  ],
  bootstrap: [
    AppComponent
    ],
  entryComponents: [DialogOverviewExampleDialog]
})
export class AppModule { }
