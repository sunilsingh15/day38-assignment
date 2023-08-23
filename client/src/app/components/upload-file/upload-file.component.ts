import { Component, ElementRef, ViewChild, inject } from '@angular/core';
import { Router } from '@angular/router';
import { UploadService } from 'src/app/upload.service';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent {

  service = inject(UploadService);
  router = inject(Router);

  @ViewChild('toUpload')
  toUpload!: ElementRef;

  id: string = '';

  processForm() {
    console.log(this.toUpload.nativeElement.files[0]);

    this.service.uploadSong(this.toUpload).subscribe((result) => {
      this.id = result.success;
      this.router.navigate(['songs/' + this.id]);
    })
  }

}
