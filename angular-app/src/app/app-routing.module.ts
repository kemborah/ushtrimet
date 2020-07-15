import{NgModule}from'@angular/core';
import {Routes, RouterModule}from '@angular/router';
import {SubjectsListComponent as SubjectsListComponent}from './components/subjects-list/subjects-list.component';
import {SubjectDetailsComponent}from './components/subject-details/subject-details.component';
import { AddSubjectComponent} from './components/add-subject/add-subject.component';


const routes: Routes = [
{path: '', redirectTo: 'subjects', pathMatch: 'full'},
{path: 'subjects', component: SubjectsListComponent},
{path: 'subjects/:id', component: SubjectDetailsComponent},
{path: 'add', component: AddSubjectComponent}];

@NgModule({
imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
