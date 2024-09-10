import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotationEtoileComponent } from './notation-etoile.component';

describe('NotationEtoileComponent', () => {
  let component: NotationEtoileComponent;
  let fixture: ComponentFixture<NotationEtoileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotationEtoileComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NotationEtoileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
