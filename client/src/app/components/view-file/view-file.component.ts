import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-file',
  templateUrl: './view-file.component.html',
  styleUrls: ['./view-file.component.css']
})
export class ViewFileComponent implements OnInit {

  activatedRoute = inject(ActivatedRoute);
  songID: string = '';
  songURL: string = 'http://localhost:8080/api/songs/';

  ngOnInit(): void {

    this.songID = this.activatedRoute.snapshot.params['id'];
    this.songURL = this.songURL + this.songID;
  }

}
