import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReplyToPostComponent } from './reply-to-post.component';

describe('ReplyToPostComponent', () => {
  let component: ReplyToPostComponent;
  let fixture: ComponentFixture<ReplyToPostComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReplyToPostComponent]
    });
    fixture = TestBed.createComponent(ReplyToPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
