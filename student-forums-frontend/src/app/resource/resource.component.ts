import { Component, Input } from '@angular/core';
import { Resource } from '../resource';
import { DatePipe } from '@angular/common';



@Component({
  selector: 'app-resource',
  templateUrl: './resource.component.html',
  styleUrls: ['./resource.component.css'],
  providers: [DatePipe]
})
export class ResourceComponent {
  @Input() resource: Resource = new Resource("","","","",false,new Date(),"","");
  @Input() displayType: string = "";
  constructor(private datePipe:DatePipe){
    console.log("display type: ",this.displayType)
  }
  returnDate(dateString:Date) {
    return this.datePipe.transform(dateString, 'dd/MM/yyyy')
  }
}
