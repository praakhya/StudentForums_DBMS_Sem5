import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostReplyComponent } from './post-reply.component';

describe('PostReplyComponent', () => {
  let component: PostReplyComponent;
  let fixture: ComponentFixture<PostReplyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostReplyComponent]
    });
    fixture = TestBed.createComponent(PostReplyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
