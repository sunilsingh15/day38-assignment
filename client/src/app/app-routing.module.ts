import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UploadFileComponent } from './components/upload-file/upload-file.component';
import { ViewFileComponent } from './components/view-file/view-file.component';

const routes: Routes = [
  { path: '', component: UploadFileComponent, title: "Upload your song" },
  {path: 'songs/:id', component: ViewFileComponent},
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
