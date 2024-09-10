import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemAvisComponent } from './item-avis.component';

describe('ItemAvisComponent', () => {
  let component: ItemAvisComponent;
  let fixture: ComponentFixture<ItemAvisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ItemAvisComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ItemAvisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
