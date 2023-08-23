import { HttpClient } from '@angular/common/http';
import { ElementRef, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor(private http: HttpClient) { }

  uploadSong(song: ElementRef): Observable<any> {
    const formData = new FormData();

    formData.set("song", song.nativeElement.files[0]);
    return this.http.post("http://localhost:8080/api/upload", formData);
  }
}
